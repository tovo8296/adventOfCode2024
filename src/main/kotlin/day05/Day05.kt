package day05

data class Rule(val x: Int, val y: Int)

fun main() {
    val rules = parseRules(input)
    val updates = parseUpdates(input)
    val valid = updates.filter { isValid(it, rules) }
    val sumMiddle = valid.map { it[it.size / 2] }.sum()
    println(sumMiddle)
}

fun isValid(update: List<Int>, rules: List<Rule>): Boolean {
    update.forEachIndexed { index, page ->
        rules.filter { it.x == page }.forEach { rule ->
            if (!isValidForRule(update, rule)) {
                return false
            }
        }
        rules.filter { it.y == page }.forEach { rule ->
            if (!isValidForRule(update, rule)) {
                return false
            }
        }
    }
    return true
}

fun isValidForRule(update: List<Int>, rule: Rule): Boolean {
    val posX = update.indexOf(rule.x)
    val posY = update.indexOf(rule.y)
    return posX == -1 || posY == -1 || posX < posY
}

fun parseRules(input: String): List<Rule> {
    return input.lines().takeWhile { it.trim().isNotEmpty() }.map {
        val numbers = it.split("|").map { it.toInt() }
        Rule(numbers[0], numbers[1])
    }
}

fun parseUpdates(input: String): List<List<Int>> {
    val empty = input.lines().indexOfFirst { it.trim().isEmpty() }
    return input.lines().drop(empty + 1).map {
        it.split(",").map { it.toInt() }
    }
}




val input = """
83|24
88|31
88|42
34|24
34|74
34|89
36|99
36|84
36|71
36|11
55|27
55|11
55|41
55|88
55|85
75|85
75|13
75|38
75|82
75|84
75|22
85|46
85|88
85|67
85|32
85|72
85|41
85|28
81|25
81|71
81|34
81|11
81|19
81|99
81|91
81|54
67|71
67|25
67|84
67|87
67|46
67|83
67|42
67|34
67|76
23|27
23|12
23|67
23|99
23|97
23|54
23|75
23|13
23|94
23|63
31|23
31|45
31|82
31|71
31|76
31|42
31|27
31|53
31|83
31|85
31|87
63|66
63|99
63|35
63|32
63|98
63|84
63|67
63|31
63|94
63|19
63|11
63|97
71|27
71|82
71|74
71|21
71|53
71|45
71|55
71|63
71|24
71|85
71|86
71|34
71|87
25|24
25|82
25|75
25|89
25|63
25|91
25|85
25|78
25|27
25|86
25|71
25|34
25|45
25|53
19|24
19|75
19|74
19|23
19|89
19|76
19|34
19|55
19|91
19|82
19|78
19|85
19|83
19|25
19|42
96|78
96|53
96|85
96|93
96|63
96|12
96|97
96|24
96|89
96|27
96|55
96|87
96|83
96|23
96|42
96|76
32|91
32|34
32|71
32|98
32|12
32|35
32|66
32|31
32|45
32|23
32|87
32|74
32|86
32|22
32|53
32|24
32|19
87|13
87|99
87|86
87|82
87|63
87|27
87|94
87|75
87|53
87|24
87|12
87|89
87|85
87|78
87|38
87|55
87|93
87|97
76|85
76|86
76|23
76|89
76|87
76|28
76|24
76|12
76|97
76|83
76|75
76|21
76|27
76|63
76|82
76|36
76|55
76|78
76|53
97|66
97|46
97|13
97|11
97|67
97|31
97|84
97|81
97|38
97|35
97|72
97|22
97|94
97|45
97|32
97|71
97|98
97|54
97|25
97|34
54|87
54|84
54|31
54|24
54|21
54|96
54|25
54|45
54|46
54|91
54|34
54|76
54|66
54|35
54|19
54|98
54|42
54|22
54|71
54|53
54|74
66|75
66|96
66|34
66|76
66|45
66|82
66|85
66|23
66|24
66|53
66|87
66|83
66|91
66|31
66|19
66|55
66|71
66|21
66|12
66|86
66|42
66|74
28|84
28|46
28|38
28|93
28|31
28|67
28|99
28|13
28|72
28|98
28|22
28|88
28|94
28|32
28|19
28|81
28|66
28|54
28|41
28|35
28|25
28|97
28|36
21|89
21|28
21|36
21|99
21|97
21|55
21|67
21|23
21|85
21|38
21|13
21|27
21|94
21|63
21|78
21|24
21|72
21|81
21|12
21|11
21|86
21|82
21|75
21|93
27|89
27|46
27|22
27|54
27|93
27|94
27|81
27|84
27|97
27|98
27|63
27|32
27|41
27|35
27|11
27|67
27|13
27|38
27|28
27|99
27|78
27|72
27|36
27|88
38|35
38|71
38|88
38|98
38|54
38|32
38|99
38|19
38|46
38|11
38|45
38|76
38|41
38|91
38|34
38|84
38|22
38|72
38|25
38|74
38|66
38|31
38|67
38|96
42|85
42|11
42|97
42|36
42|75
42|78
42|38
42|24
42|89
42|55
42|53
42|13
42|63
42|23
42|27
42|94
42|87
42|21
42|28
42|81
42|12
42|82
42|86
42|93
46|86
46|53
46|25
46|12
46|74
46|31
46|76
46|96
46|42
46|55
46|98
46|82
46|87
46|19
46|21
46|66
46|24
46|75
46|91
46|23
46|45
46|83
46|34
46|71
45|87
45|78
45|83
45|82
45|27
45|12
45|55
45|91
45|89
45|63
45|74
45|36
45|86
45|42
45|23
45|93
45|24
45|21
45|75
45|96
45|85
45|28
45|76
45|53
24|63
24|75
24|85
24|41
24|38
24|13
24|86
24|94
24|67
24|72
24|23
24|97
24|99
24|27
24|55
24|28
24|82
24|81
24|93
24|89
24|12
24|11
24|36
24|78
99|88
99|31
99|32
99|66
99|76
99|41
99|42
99|83
99|91
99|98
99|72
99|34
99|22
99|67
99|25
99|74
99|45
99|96
99|71
99|46
99|84
99|54
99|19
99|35
12|11
12|85
12|28
12|41
12|13
12|93
12|38
12|88
12|99
12|81
12|27
12|54
12|97
12|82
12|63
12|89
12|84
12|55
12|75
12|78
12|36
12|94
12|72
12|67
53|28
53|38
53|78
53|55
53|89
53|85
53|23
53|12
53|93
53|13
53|63
53|11
53|82
53|97
53|72
53|27
53|24
53|21
53|36
53|99
53|81
53|94
53|75
53|86
35|31
35|12
35|21
35|66
35|45
35|74
35|24
35|96
35|98
35|71
35|75
35|76
35|19
35|42
35|34
35|83
35|23
35|87
35|53
35|91
35|86
35|46
35|25
35|55
91|76
91|89
91|36
91|55
91|87
91|78
91|97
91|21
91|75
91|42
91|53
91|82
91|63
91|12
91|86
91|24
91|96
91|23
91|85
91|28
91|93
91|83
91|27
91|74
89|28
89|63
89|72
89|81
89|94
89|35
89|98
89|36
89|99
89|78
89|67
89|88
89|13
89|97
89|93
89|32
89|22
89|11
89|41
89|66
89|54
89|84
89|38
89|46
84|88
84|19
84|91
84|74
84|22
84|96
84|66
84|45
84|76
84|25
84|71
84|83
84|46
84|32
84|35
84|42
84|53
84|86
84|24
84|98
84|21
84|34
84|31
84|87
86|94
86|67
86|55
86|75
86|13
86|82
86|78
86|38
86|97
86|27
86|28
86|99
86|72
86|85
86|41
86|89
86|11
86|93
86|12
86|23
86|54
86|36
86|63
86|81
98|96
98|53
98|55
98|85
98|71
98|91
98|42
98|21
98|23
98|24
98|87
98|66
98|12
98|76
98|82
98|25
98|75
98|31
98|86
98|45
98|34
98|19
98|74
98|83
11|32
11|22
11|34
11|99
11|71
11|98
11|74
11|46
11|41
11|54
11|67
11|84
11|88
11|31
11|25
11|19
11|76
11|72
11|35
11|91
11|83
11|66
11|45
11|96
41|19
41|84
41|32
41|98
41|22
41|35
41|74
41|91
41|25
41|21
41|45
41|88
41|54
41|83
41|34
41|66
41|42
41|96
41|46
41|31
41|53
41|76
41|87
41|71
22|25
22|91
22|83
22|53
22|46
22|74
22|12
22|23
22|96
22|87
22|31
22|98
22|55
22|24
22|34
22|66
22|71
22|45
22|35
22|19
22|21
22|42
22|86
22|76
72|71
72|25
72|35
72|19
72|22
72|46
72|67
72|83
72|88
72|32
72|76
72|74
72|54
72|31
72|91
72|96
72|42
72|98
72|84
72|66
72|34
72|45
72|87
72|41
94|67
94|35
94|11
94|84
94|38
94|25
94|71
94|34
94|41
94|31
94|72
94|99
94|66
94|19
94|22
94|91
94|74
94|98
94|46
94|81
94|45
94|32
94|54
94|88
93|31
93|97
93|11
93|81
93|54
93|72
93|67
93|71
93|88
93|25
93|46
93|66
93|13
93|99
93|36
93|94
93|41
93|38
93|22
93|98
93|32
93|84
93|19
93|35
82|84
82|35
82|54
82|63
82|81
82|99
82|85
82|28
82|22
82|32
82|38
82|72
82|36
82|94
82|67
82|93
82|13
82|88
82|78
82|97
82|41
82|27
82|89
82|11
13|71
13|94
13|46
13|99
13|25
13|98
13|32
13|45
13|38
13|88
13|91
13|84
13|41
13|31
13|11
13|54
13|34
13|66
13|35
13|19
13|22
13|81
13|72
13|67
74|86
74|97
74|21
74|63
74|36
74|53
74|87
74|85
74|75
74|23
74|76
74|83
74|27
74|82
74|28
74|55
74|78
74|13
74|12
74|96
74|42
74|24
74|89
74|93
78|36
78|84
78|98
78|11
78|13
78|88
78|67
78|93
78|99
78|54
78|46
78|94
78|97
78|32
78|81
78|41
78|28
78|22
78|38
78|66
78|63
78|35
78|31
78|72
83|42
83|53
83|82
83|12
83|27
83|86
83|23
83|75
83|55
83|21
83|97
83|93
83|81
83|85
83|38
83|13
83|63
83|78
83|94
83|89
83|28
83|87
83|36
88|25
88|74
88|24
88|83
88|66
88|71
88|87
88|98
88|96
88|22
88|86
88|45
88|34
88|19
88|76
88|46
88|35
88|23
88|91
88|32
88|53
88|21
34|91
34|21
34|93
34|45
34|42
34|85
34|27
34|86
34|53
34|23
34|75
34|55
34|12
34|28
34|82
34|78
34|63
34|83
34|96
34|76
34|87
36|46
36|98
36|34
36|88
36|35
36|54
36|97
36|72
36|94
36|32
36|31
36|41
36|67
36|66
36|25
36|22
36|13
36|38
36|19
36|81
55|81
55|67
55|28
55|63
55|89
55|82
55|54
55|78
55|84
55|36
55|94
55|99
55|13
55|32
55|72
55|97
55|38
55|93
55|75
75|88
75|94
75|41
75|28
75|67
75|93
75|27
75|63
75|99
75|81
75|78
75|11
75|36
75|54
75|97
75|32
75|72
75|89
85|36
85|89
85|78
85|27
85|22
85|93
85|13
85|99
85|63
85|11
85|84
85|38
85|94
85|35
85|54
85|81
85|97
81|45
81|46
81|88
81|72
81|31
81|74
81|41
81|32
81|96
81|38
81|22
81|98
81|84
81|35
81|66
81|67
67|74
67|54
67|31
67|66
67|45
67|96
67|53
67|35
67|88
67|32
67|91
67|19
67|41
67|98
67|22
23|89
23|41
23|55
23|36
23|28
23|82
23|78
23|81
23|11
23|38
23|85
23|93
23|72
23|84
31|25
31|96
31|75
31|74
31|21
31|89
31|12
31|55
31|24
31|86
31|34
31|91
31|19
63|41
63|28
63|22
63|36
63|88
63|38
63|81
63|13
63|93
63|72
63|54
63|46
71|28
71|83
71|91
71|96
71|23
71|42
71|12
71|78
71|75
71|89
71|76
25|23
25|87
25|55
25|12
25|42
25|74
25|83
25|21
25|76
25|96
19|21
19|12
19|86
19|96
19|71
19|87
19|45
19|53
19|27
96|94
96|21
96|82
96|75
96|13
96|86
96|36
96|28
32|25
32|21
32|46
32|96
32|42
32|76
32|83
87|21
87|11
87|28
87|81
87|36
87|23
76|81
76|94
76|93
76|13
76|42
97|19
97|88
97|99
97|41
54|83
54|32
54|88
66|25
66|27
28|11

46,98,96,88,42
83,87,21,23,12,85,27,89,63,28,97,13,81
55,82,85,27,89,63,28,93,13,94,81,11,99,41,54
22,35,46,98,66,31,25,71,34,74,96,76,83,42,87,53,21,24,86,23,12
35,46,98,66,19,71,34,45,91,74,96,83,42,87,21,23,55
67,41,54,84,88,32,22,35,46,98,66,31,25,71,45,91,74,76,87
97,41,99,98,25,71,94,34,72,22,54,84,32,19,46
75,42,87,71,74,46,23
74,96,76,83,42,53,21,24,86,23,12,75,82,85,27,89,63,28,93,36,97
55,75,82,27,89,78,28,93,97,94,81,38,11,72,67,41,54
25,71,91,76,83,42,87,53,86,85,27
11,97,99,63,84,38,28,93,72,13,81,41,46
27,78,42,34,53,21,25,71,82,86,76,89,55,75,85,12,96,91,23,87,24,74,83
19,45,91,74,96,76,42,87,53,21,24,86,23,12,55,75,82,85,27
94,81,38,99,72,67,41,54,84,88,32,22,35,46,98,66,31,19,25,71,34,45,91
11,85,28,41,27,72,38,36,97,55,75,63,82,78,12,94,67,99,89,86,23,13,81
42,87,86,55,75,85,27,89,78,63,28,93,36,97,13,94,38
19,66,86,25,91
66,19,74,84,53,54,21,88,46
19,25,71,96,76,87,53,21,23,12,82,85,89
74,76,53,23,12,75,85,27,93,36,97
63,28,97,13,94,11,99,72,41,54,88,22,98
93,75,27,24,11,55,28,63,99,36,13,85,38
24,99,93,78,89,38,82,12,81,75,27,67,23,86,63,97,36,55,85
91,76,32,54,83,87,71,34,88,84,31,98,67,41,45
41,42,22,84,45,88,31
63,93,82,86,12,23,75,42,76,24,94
31,19,25,71,34,45,91,74,96,76,83,87,53,24,23,12,55,75,82,85,27
74,96,83,86,12,55,75,36,97
54,72,84,41,67,99,35,94,88,81,13,98,38,19,97,66,32,22,46
96,76,83,53,21,24,86,12,27,89,93,97,13
72,71,91,99,54,84,76,34,96,41,11,67,22,35,32,19,25
84,88,22,46,98,25,71,42,87,53,24
34,74,31,66,46,76,84,53,21,19,96,54,42,25,35,71,98
99,72,67,41,54,84,88,32,22,35,46,98,66,31,19,71,34,45,91,74,96,76,83
36,97,13,94,81,38,11,99,72,67,41,54,84,88,32,22,35,98,66,31,19,25,71
35,66,19,74,21,86,55
87,53,21,24,86,55,75,82,85,27,89,78,63,28,93,97,13,94,81,38,11
35,32,98,97,19,54,71,36,13
24,86,23,12,55,75,82,85,27,89,78,63,28,93,97,13,94,38,11,99,67
28,72,67,41,46,31,19
54,84,22,46,98,31,19,25,71,34,45,91,74,96,21
86,97,93,63,36,38,27,85,11,99,89,55,75,82,94
23,75,27,89,78,36,99,41,54
54,84,88,32,22,35,46,98,66,31,25,71,34,91,74,76,83,53,21
83,42,21,24,23,12,55,75,85,27,89,78,63,13,94
19,88,21,84,83,35,71,24,32,98,46,91,45
24,86,12,55,75,82,27,89,78,63,28,13,94,38,99,72,67
83,21,24,12,75,82,27,89,78,63,28,93,97,13,81
66,31,71,34,45,83,87,53,85
67,54,84,32,35,98,66,19,71,34,96,76,83
81,72,84,63,35,54,97,89,94,36,98,88,32,38,28,99,46,78,13,93,22,11,41
89,12,96,25,19,74,71,42,24,75,76,21,91,34,86
46,98,31,25,71,34,45,91,96,76,53,21,86,55,75
12,55,75,82,85,27,78,63,28,93,36,13,94,81,38,67,84
89,78,11,67,41
71,34,45,91,96,76,83,42,24,86,85,27,89,78,63
71,91,72,46,84,19,41,35,31,25,11,74,81
46,66,19,25,71,34,45,74,96,76,83,42,87,53,21,24,86,23,12,55,75
74,66,83,42,86,45,21,91,23,75,71,24,31,46,53
22,35,46,98,66,31,19,25,71,34,91,74,96,76,83,42,87,53,21,24,86,23,12
63,89,28,27,21,42,96,91,36,23,55,87,24,82,12
53,27,13,38,11
81,99,67,35,46,98,66,25,74
45,55,96,24,76,27,82,86,21,25,83
88,32,22,98,19,25,71,34,45,91,74,76,83,42,21,24,86
94,81,38,11,67,54,84,88,32,22,35,98,19,71,45
74,96,87,53,23,12,55,75,82,85,27,78,28,36,97
94,81,11,99,72,41,84,32,22,35,46,66,31,19,25,71,91
12,55,75,82,85,89,78,63,28,93,36,97,13,94,38,11,99,72,67,54,84
75,82,27,63,93,94,38,99,32
63,93,36,97,13,81,11,72,67,41,54,88,32,35,98,66,31
21,27,28,36,97,13,81,38,11
45,91,74,96,76,83,42,87,53,21,23,12,55,75,82,85,27,89,78,63,93
78,63,28,93,36,97,13,94,81,38,11,99,72,67,41,54,84,88,32,22,35,46,66
45,91,76,83,24,86,85,27,93
96,42,87,23,55,97,13
81,41,88,22,31,71,34,45,74
89,11,23,28,12,94,99,97,53,36,85,82,63
63,78,82,84,89,32,81
86,53,21,87,83,32,76,42,35,22,71,24,31,66,45,46,34,88,25,19,74
76,42,55,75,85,89,63,93,36,97,94
35,66,31,19,25,71,34,45,74,83,42,53,24,86,23,12,55
27,74,23,24,76,31,85,86,75
28,93,36,13,94,38,72,67,41,54,84,32,35,98,66
96,76,83,42,21,24,86,23,12,55,82,78,28,93,36,97,13
13,38,99,84,88,32,35,46,98,31,19,25,71,34,45
89,28,97,13,94,81,11,99,72,41,98
36,75,78,24,27,21,82,72,28,23,99,93,63,86,55,89,11,38,81
78,36,97,13,38,99,72,54,84,88,22,98,66
88,34,54,76,71,25,91,74,84,31,19,72,35,67,98,42,41,46,22,66,32,83,45
55,82,85,27,89,78,28,93,97,94,81,38,11,67,41,84,88
76,42,86,27,89,78,94
98,66,19,34,91,96,83,87,53,23,12,55,75
46,98,66,31,19,25,71,34,45,91,74,96,76,42,87,53,21,24,86,23,12,55,75
93,38,67,41,54,84,88,22,35,98,31
46,67,31,19,87,83,76,88,35
19,71,45,91,74,96,76,83,87,53,21,24,86,23,75,82,89
87,53,21,24,86,12,55,75,82,27,89,78,63,28,93,97,81,38,11
78,93,72,81,99,97,32,66,41,63,28
53,86,55,85,63,28,93,97,81,11,99
93,13,23,87,63,24,94,21,81,11,38
46,97,13,84,22,63,66,94,78,99,54,93,11,67,88,35,36
82,85,89,78,63,93,36,97,13,94,81,38,11,99,72,41,54,84,88,32,22
82,34,96,55,76,28,42,45,63,12,91
32,35,46,98,66,19,25,71,34,91,74,96,76,42,21,24,23
11,67,41,54,84,88,22,46,71,34,91,96,76
86,23,55,75,82,85,89,78,63,28,93,36,97,13,94,81,38,11,99,67,41
97,13,94,81,99,41,54,32,35,46,98,66,25,71,34
66,25,21,24,86,55,85
35,27,85,22,99,41,78,89,54,11,93,67,84,72,81
98,34,21,74,23,32,76,66,35,53,19,31,83,71,42
89,76,78,75,28
97,82,85,96,55,13,42,93,86
45,66,83,35,53,98,74,22,76,21,23,24,34,87,46
32,46,25,45,74,42,53
12,42,93,36,24,85,91
21,24,86,12,85,78,63,38,99
75,78,36,13,99
93,36,46,97,67,84,13,72,35,25,88,81,54,41,99,32,94
23,21,19,53,66,87,46,24,83,45,12,22,25
86,75,28,13,81,38,41
28,97,11,99,72,41,54,32,35,31,19
35,31,87,32,66,24,84
76,71,31,72,46,22,66,35,84,98,99,96,34,19,83,45,32,91,54,67,41
12,83,76,74,86,45,55,28,21,85,93
55,86,85,13,89,24,82,67,28,23,81
45,84,11,67,74,96,19,54,22,66,35,32,46,71,72,38,34
41,97,28,75,81,99,78,82,67,36,84,27,32,88,85,89,11,93,38,63,13
88,32,22,46,98,66,31,71,91,96,76,24,86
94,99,38,66,46
25,19,24,91,71,74,31
46,32,63,93,11,81,41,38,98,84,97,28,99,36,88,13,35
41,84,88,32,22,35,46,98,66,31,19,25,71,34,45,91,74,76,83,42,53
38,72,31,13,28,63,32,11,93
87,53,21,24,23,12,55,75,82,85,27,78,63,28,93,36,13,94,11
54,72,81,67,89,46,84,99,36,13,32,98,38,35,41,94,28,97,22,63,93
46,27,41,89,54,36,63,81,99,84,67
11,67,41,84,88,32,98,66,31,19,34,45,91
86,74,96,27,53,25,31,34,45,21,83,23,24
13,81,41,35,98,31,19,34,45
72,54,88,32,22,46,66,31,25,34,45,96,76,83,42
27,89,78,63,28,93,36,97,94,81,38,11,99,72,67,41,54,88,22,35,46
86,23,85,63,93,36,94,11,99,67,41
12,55,82,85,27,89,63,28,97,94,81,11,54
86,23,75,82,93,11,41
11,99,67,41,54,84,88,32,35,46,31,19,25,34,74,96,76
53,38,82,99,85,94,63,89,93,55,78,86,36,97,75,28,27,24,23,21,13
96,35,76,19,83,25,91,34,53,98,71,86,22,42,74,66,24,46,31,87,88
82,85,27,89,78,63,28,93,36,97,94,38,99,67,41,84,88,32,22
25,45,91,74,96,76,42,87,53,24,12,85,27,89,78
42,87,53,21,24,23,12,55,85,27,89,78,63,93,36,94,38
21,86,23,12,75,82,85,89,78,28,97,13,81,11,72
94,54,22,98,31,19,34
85,27,89,78,28,93,36,13,94,38,11,72,41,54,84,88,32,22,35
88,32,22,35,46,98,66,31,19,25,71,34,91,74,96,76,83,42,87,53,21,24,86
19,96,91,31,45,71,42,74,75,12,34,76,27,87,82,21,25,53,55
22,35,46,98,66,19,25,71,34,45,91,76,83,42,87,53,21,24,86,23,12
13,94,81,11,99,72,67,41,54,84,32,22,35,46,66,31,19,25,71,34,45
99,46,71,67,76,66,45,54,11
54,32,46,98,96
71,91,74,21,24,12,85
76,83,42,87,53,21,24,86,23,12,55,75,82,85,27,89,78,63,28,93,36,13,94
85,74,12,97,24,27,42,83,63,93,36
21,45,46,87,12,83,76,25,35,19,42,31,71,23,22,74,34,96,91,86,53
78,93,12,86,23,63,87
88,66,31,19,71,34,45,74,76,42,53
54,84,88,22,46,66,74,42,87,53,21
75,85,27,89,63,13,81,38,32
84,35,98,66,19,25,71,34,45,91,74,96,76,83,42,87,53,21,24
11,99,72,67,41,54,84,32,22,35,98,66,31,19,25,71,34,74,76
25,34,45,74,96,76,83,42,87,53,21,24,86,23,55,75,82,85,27,89,78
71,34,45,74,96,76,83,42,87,53,21,24,86,23,12,55,75,82,85,27,89,78,63
85,82,75,21,81,83,24,27,87,78,55,89,97
35,46,98,66,31,19,25,71,34,91,74,96,76,83,42,87,53,21,24,86,23,12,55
32,22,35,46,98,31,25,71,91,74,76,83,53,21,24,86,23
42,87,53,21,24,86,23,55,75,82,85,27,89,78,63,28,93,36,97,13,94,81,38
84,46,31,19,34
28,67,54,84,88,32,35,46,98,31,19
28,11,99,72,32,22,98,31,19
13,94,81,38,41,54,84,22,35,46,98,66,31,19,25,34,45
85,27,89,63,93,54,84,32,35
88,34,19,99,74,41,45,91,11,35,32,84,98,76,71,54,22,96,67,66,46
99,84,91,35,74,81,34
81,98,72,66,54,93,35,63,22,94,99,36,41,28,84,38,97,13,11
67,88,46,81,13,41,66,34,97,38,31,99,84
93,36,97,13,94,81,38,11,99,54,84,88,32,22,35,46,98,31,25
98,66,31,42,87,75,82
89,72,84,93,81,97,28,99,88,41,11,27,32,78,63,54,38,13,75,94,36
75,82,85,27,78,63,28,93,36,97,13,94,38,11,99,67,41,54,84,88,32
38,11,99,67,84,88,35,46,98,66,31,19,91,74,96
84,32,98,31,45,96,24
84,36,82,22,85,13,72,38,67,88,94,99,32
28,93,36,97,13,94,81,11,72,67,41,54,84,88,32,22,35,46,98,66,31
24,86,75,27,89,78,63,28,93,97,13,94,81,38,67
42,53,23,12,55,75,85,27,89,28,36,97,94,81,38
41,54,84,88,32,22,35,46,98,66,31,19,25,71,34,45,91,74,96,76,83,42,53
21,71,86,83,89,55,45,53,27,24,75,42,87
22,42,46,87,53,23,83,32,34,25,21,19,86
82,78,36,97,38,11,99,72,67,41,84,88,22
41,78,97,86,99,28,85,82,89,27,75,13,72,11,12,63,94
""".trimIndent()