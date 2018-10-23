const NAME = "John Dow"; // constant, can not be modified

console.log(x);
if (true) {
    let x = 12; // can not be used outside if
    console.log(x);
}

// 6 will be printed each time
for (var i = 1; i < 6; i++) {
   setTimeout(function() {
      console.log(i);
   }, 1000);
}

// 1 2 3 4 5 6 will be printed, because of new lexical environment
for (let i = 1; i < 6; i++) {
   setTimeout(function() {
      console.log(i);
   }, 1000);
}