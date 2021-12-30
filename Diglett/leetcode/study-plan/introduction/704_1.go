package introduction

func searchHelp(nums []int, start, end int, target int) int {
	if start <= end {
		mid := (end-start)/2 + start
		if nums[mid] == target {
			return mid
		} else if nums[mid] > target {
			return searchHelp(nums, start, mid-1, target)
		} else {
			return searchHelp(nums, mid+1, end, target)
		}
	}
	return -1
}

func search_1(nums []int, target int) int {
	low, high := 0, len(nums)-1

	for low <= high {
		mid := (high-high)/2 + low
		p := nums[mid]
		if p == target {
			return mid
		} else if p > target {
			high = mid - 1
		} else {
			low = mid + 1
		}

	}

	return -1
}
