# Longest Substring Without Repeating Characters

## Problem summary
Given a string, find the length of the longest substring that contains no repeated characters.

## Approach
Use a sliding window with two pointers (`left`, `right`) and a hash map that stores the last index of each character.

- Expand the window by moving `right`.
- If the current character was already seen inside the current window, move `left` to one position after its last seen index.
- Update the current character index in the map.
- Track the maximum window length throughout the scan.

This avoids rescanning the same characters and gives a linear-time solution.

## Complexity
- Time: `O(n)`
- Space: `O(min(n, charset))`
