package com.lyz.ddedss_springboot.util;

import cn.hutool.core.util.RandomUtil;

import java.util.*;

/**
 * KMeans算法
 * 根据学生的最近一次考试总分，和学生的性别，进行聚类
 */
public class KMeans {

    /**
     * 使用kmeans算法对学生进行分组
     * 传入数据
     * n: 需要分为n组
     * Map: 学号，[考试总分，性别]
     */
    public List<List<Integer>> sort(int n, Map<Integer, Double[]> students) {
        // 初始化n个簇点
        // x 总分
        // y 性别
        List<Double[]> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nodes.add(new Double[]{RandomUtil.randomDouble(600, 1200), RandomUtil.randomDouble(0, 1)});
        }
        // 1000次计算
        List<List<Integer>> studentIdGroup = null;
        for (int i = 0; i < 1000; i++) {
            studentIdGroup = new ArrayList<List<Integer>>() {
                {
                    for (int i = 0; i < n; i++) {
                        add(new ArrayList<>());
                    }
                }
            };
            // 每个学生分别计算欧氏距离
            for (Integer studentId : students.keySet()) {
                int nearestNodeIndex = getNearestNode(nodes, students.get(studentId));
                studentIdGroup.get(nearestNodeIndex).add(studentId);
            }
            // 重新计算簇点
            nodes = reCalculateNodes(studentIdGroup, students);
        }
        return studentIdGroup;
    }

    /**
     * 计算欧氏距离
     */
    private double calDistance(Double[] node1, Double[] node2) {
        return Math.sqrt(Math.pow((node1[0] - node2[0]), 2) + Math.pow((node1[1] - node2[1]), 2));
    }

    /**
     * 计算距离哪个簇点最近
     * 返回距离最近的簇点下标
     */
    private int getNearestNode(List<Double[]> nodes, Double[] student) {
        int index = 0;
        double distance = -1;
        for (int i = 0; i < nodes.size(); i++) {
            double tmpDistance = calDistance(nodes.get(i), student);
            if (tmpDistance > distance) {
                distance = tmpDistance;
                index = i;
            }
        }
        return index;
    }

    /**
     * 重新计算簇点
     */
    private List<Double[]> reCalculateNodes(List<List<Integer>> studentIdGroup, Map<Integer, Double[]> students) {
        List<Double[]> nodes = new ArrayList<>();
        // 分别计算簇点坐标
        for (int i = 0; i < studentIdGroup.size(); i++) {
            List<Integer> studentIds = studentIdGroup.get(i);
            double x = 0;
            double y = 0;
            for (Integer studentId : studentIds) {
                Double[] doubles = students.get(studentId);
                x += doubles[0];
                y += doubles[1];
            }
            x /= studentIds.size();
            y /= studentIds.size();
            nodes.add(new Double[]{x, y});
        }
        return nodes;
    }

}
