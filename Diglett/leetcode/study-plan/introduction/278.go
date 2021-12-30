package introduction

func isBadVersion(version int) bool {
	return true
}

func firstBadVersionHelper(start, end int) int {
	if start <= end {
		if isBadVersion(start) {
			return start
		}
		mid := (end-start)/2 + start
		if isBadVersion(mid) {
			return firstBadVersionHelper(start+1, mid-1)
		} else if isBadVersion(end) {
			return firstBadVersionHelper(mid+1, end-1)
		} else {
			return end + 1
		}
	}
	return end +1
}

func firstBadVersion(n int) int {
	return firstBadVersionHelper(1, n)
}
