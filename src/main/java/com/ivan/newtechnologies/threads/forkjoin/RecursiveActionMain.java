package com.ivan.newtechnologies.threads.forkjoin;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.stream.IntStream;

public class RecursiveActionMain {

    public static void main(String[] args) {
        final int[] arrToSquare = IntStream.range(1, 20).toArray();

        final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        forkJoinPool.invoke(new SquareAction(arrToSquare, 0, arrToSquare.length));

        System.out.println(Arrays.toString(arrToSquare));
    }

    private static class SquareAction extends RecursiveAction {

        private final int[] arrToSquare;
        private final int startIndex;
        private final int endIndex;

        public SquareAction(final int[] arrToSquare, final int startIndex, final int endIndex) {
            this.arrToSquare = arrToSquare;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        protected void compute() {
            for (int i = this.startIndex; i < this.endIndex; i++) {
                this.arrToSquare[i] *= this.arrToSquare[i];
            }
        }
    }


}
