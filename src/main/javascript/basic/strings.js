// Interpolation
var name = "John";
let address = {
    city: "SPB"
};
var greeting = `Hello, ${name}. Welcome to \`${address.city}\``;

// Tagged templates
function interpolate(strings, name, time) {
    var str0 = strings[0]; // 'Hello, '
    var str1 = strings[1]; // '! Time is '

    return `${str0}'${name}', my friend${str1}${time}`
}
var name = 'John';
interpolate`Hello, ${name}! Time is ${new Date()}`

// Extended support using Unicode within strings and regular expressions
"𠮷".length === 2
"𠮷".match(/./u)[0].length === 2
"𠮷" === "\uD842\uDFB7"
"𠮷" === "\u{20BB7}" // No analogue in ECMAScript 5
"𠮷".codePointAt(0) == 0x20BB7 // No analogue in ECMAScript 5
for (let codepoint of "𠮷") console.log(codepoint) // No analogue in ECMAScript 5