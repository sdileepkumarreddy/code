# Top K Frequent Elements

## Problem summary
Given an integer array and an integer `k`, return the `k` most frequent elements.

## Approach
Count occurrences with a hash map, then place each value into a bucket indexed by its frequency.

- First pass: build `value -> count`.
- Second pass: place each value in `buckets[count]`.
- Walk buckets from highest frequency down until `k` results are collected.

This avoids sorting all distinct values and keeps the solution linear relative to the input size.

## Complexity
- Time: `O(n)`
- Space: `O(n)`
