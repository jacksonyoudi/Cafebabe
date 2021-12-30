package introduction

func search(nums []int, target int) int {
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
