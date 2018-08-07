package com.ivan.newtechnologies.basic

def result = ''
10.times {
    result += 'F'
}
println result

result = ''
1.upto(10) { idx ->
    result += idx + ' '
}
println result

result = ''
10.downto(1) { idx ->
    result += idx + ' '
}
println result

result = ''
2.step(4, 0.7) { num ->
    result += num + ' '
}
println result

(2..5).each {
    print "$it "
}