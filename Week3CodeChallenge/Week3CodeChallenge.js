var books = new Array();
books = ["Title", "Author", "Genre"];
var bookInfo ="";

var form = document.getElementById("myForm");
form.addEventListener("click", getBookInfo);

//still need to get information from form
function getBookInfo() {
	
}
function addNode(books) {
	for(var i = 0; i < books.length; i++) {
		bookInfo = bookInfo + " " + books[i];
	}

	var node = document.createElement("TR");
	var textNode = document.createTextNode(bookInfo);
	node.appendChild(textNode);
	document.getElementById("booksTable").appendChild(node);
}
addNode(books);


/**for(int i= 0; i < books.length; i++) {
	var item = document.createElement("td");
	item = rowInsert.insertCell(i);
}*/