package com.komal.komal.service;

import org.springframework.stereotype.Service;

@Service
public class BankerService {

    public String checkSafeState(int[][] alloc, int[][] max, int[] avail) {

        int n = alloc.length;
        int m = avail.length;

        int[][] need = new int[n][m];
        boolean[] finish = new boolean[n];
        int[] work = avail.clone();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                need[i][j] = max[i][j] - alloc[i][j];
            }
        }

        StringBuilder safeSeq = new StringBuilder();

        for (int count = 0; count < n; count++) {
            for (int i = 0; i < n; i++) {

                if (!finish[i]) {
                    boolean canRun = true;

                    for (int j = 0; j < m; j++) {
                        if (need[i][j] > work[j]) {
                            canRun = false;
                            break;
                        }
                    }

                    if (canRun) {
                        for (int j = 0; j < m; j++) {
                            work[j] += alloc[i][j];
                        }
                        finish[i] = true;
                        safeSeq.append("P").append(i).append(" ");
                    }
                }
            }
        }

        for (boolean f : finish) {
            if (!f) return "❌ Unsafe State (Deadlock Possible)";
        }

        return "✅ Safe Sequence: " + safeSeq;
    }
}