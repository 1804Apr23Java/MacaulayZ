//JavaScript Homework Part 1
var homework = {};

//1.) Return the nth fibonacci number
homework.fibonacci = function(n) {
	var num1 = 0, num2 = 1, sum;
	while(n >= 0) {
		sum = num1;
		num1 = num1 + num2;
		num2 = sum;
		n--;
	}
	return sum;
};

//2.) Sort array of integers
//sort([2,4,5,1,3,1]) = [1,1,2,3,4,5]);
homework.sort = function(array) {
	var temp;
	var sorted = true;
	while(true) {
		for(var i = 0; i < array.length; i++) {
			if(array[i+1] < array[i]) {
				temp = array[i];
				array[i] = array[i+1];
				array[i+1] = temp;
				sorted = false;
			}
		}
		if(sorted = true) {
			
		}
		sorted = true;
	}
	return array;
	
};


//3.) Return the factorial of n
homework.factorial = function(n) {
	var num1 = 0, num2 = 1, factorial;
	if(n == 1 || n == 0) {
		return 1;
	}
	while(n > 1) {
		factorial = num1;
		num1 = n*(n-1);
		num2 = factorial;
		n--;
	}
	return factorial;
};


//4.) Given array, rotate left n times and return array
//rotateLeft([1,2,3,4,5], 1);  = [2,3,4,5,1]
homework.rotateLeft = function(array, n) {
	var array;
	for(var i = 0; i < n; i++) {
		//add item to end of array
		array.splice(array.length, 0, array[i]);
		//remove item from its original position
		array.splice(array[i-1], n);
		n--;
	}
	return array;
};

//5.) Return true if the bracket is balanced or false if it isn't balanced
homework.balancedBrackets = function(bracketsString) {
	var bracketsString;
	if(bracketsString.includes('(') && bracketsString.includes(')')) {
		bracketsString = true;
	} else if(bracketsString.includes('[') && bracketsString.includes(']')) {
		bracketsString = true;
	} else if(bracketsString.includes('{') && bracketsString.includes('}')) {
		bracketsString = true;
	} else {
		bracketsString = false;
	}
	return bracketsString;
};