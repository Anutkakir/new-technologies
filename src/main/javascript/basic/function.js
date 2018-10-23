// Arrow function
setTimeout(() => {
    alert(12);
}, 10);

function() {
    [1, 2, 3].forEach((value, index, array) => {
        // 'this' and 'arguments' will be used from enclosing function
        console.log(`$index: $value`);
    })
}

// Default parameter
function sum(x, y = 3) {
    return x + y;
}
sum(4); // 7
sum(4, 8); // 12

// Rest parameter
function ddd(a, ...b) {
    // b can be used as an array
}
ddd(12);
ddd(12, 25);
ddd(12, 25, 8);

// Spread operator
var params = ["hello", true];
var other = [1, 2, …params] // [1, 2, "hello", true]

function aa(a, b) {
    return a + b
}
aa(...params)
