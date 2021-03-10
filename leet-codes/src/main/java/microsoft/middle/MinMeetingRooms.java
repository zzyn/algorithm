package microsoft.middle;

import java.util.*;

/**
 * Given an array of meeting time intervals intervals where intervals[i] = [starti, endi],
 * return the minimum number of conference rooms required.
 *
 */
public class MinMeetingRooms {

    /**
     *
     * @param intervals
     * @return
     */
    public int minMeetingRooms_version_1(int[][] intervals) {

        if(intervals.length == 0) {
            return 0;
        }

        //min heap
        PriorityQueue<Integer> allocator =
                new PriorityQueue<Integer>(
                        intervals.length,
                        new Comparator<Integer>() {
                            public int compare(Integer a, Integer b) {
                                return a - b;
                            }
                        });

        // Sort the intervals by start time
        Arrays.sort(
                intervals,
                new Comparator<int[]>() {
                    public int compare(final int[] a, final int[] b) {
                        return a[0] - b[0];
                    }
                });


        // Add the first meeting
        allocator.add(intervals[0][1]);

        // Iterate over remaining intervals
        for (int i = 1; i < intervals.length; i++) {

            // If the room due to free up the earliest is free, assign that room to this meeting.
            if (intervals[i][0] >= allocator.peek()) {
                allocator.poll();
            }

            // If a new room is to be assigned, then also we add to the heap,
            // If an old room is allocated, then also we have to add to the heap with updated end time.
            allocator.add(intervals[i][1]);
        }

        // The size of the heap tells us the minimum rooms required for all the meetings.
        return allocator.size();

    }

    //时间复杂度：O(nlog(n))
    //空间复杂度：O(n)
    public int minMeetingRooms_version_2(int[][] intervals) {
        if (intervals == null) {
            return 0;
        }

        if (intervals.length < 2) {
            return intervals.length;
        }

        int n = intervals.length;
        int[] starts = new int[n];
        int[] ends = new int[n];

        for (int i = 0; i < n; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }

        Arrays.sort(starts);
        Arrays.sort(ends);

        int start = 0, end = 0;
        int rooms = 0, activeMeeting = 0;

        while (start < n) {
            if (starts[start] < ends[end]) {
                activeMeeting++;
            } else {
                end++;
            }
            start++;
            rooms = Math.max(rooms, activeMeeting);
        }

        return rooms;
    }
}
