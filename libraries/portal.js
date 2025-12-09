// portal js 
function loadSection(section) {
    let box = document.getElementById('content');

    if (section === "search") {
        box.innerHTML = `
            <h2>Search Books</h2>
            <input type="text" id="searchBook" placeholder="Enter book name or author">
            <button class="action" onclick="searchBook()">Search</button>
            <div id="bookList"></div>
        `;
    }

    if (section === "issue") {
        box.innerHTML = `
            <h2>Issue / Return Books</h2>
            <input type="text" id="stuId" placeholder="Enter Student ID">
            <input type="text" id="bookId" placeholder="Enter Book ID">
            <button class="action" onclick="issueBook()">Issue Book</button>
            <button class="action" onclick="returnBook()">Return Book</button>
        `;
    }

    if (section === "records") {
        box.innerHTML = `
            <h2>Student Library Records</h2>

            <div class="record-card">
                <h3>Student: Rohan Das</h3>
                <p>ID: CS102</p>
                <p>Books Issued: 2</p>
                <p>Pending Fine: ₹ 0</p>
            </div>

            <div class="record-card">
                <h3>Student: Priya Sen</h3>
                <p>ID: CS108</p>
                <p>Books Issued: 1</p>
                <p>Pending Fine: ₹ 20</p>
            </div>
        `;
    }

    if (section === "notices") {
        box.innerHTML = `
            <h2>Library Notices</h2>
            <ul>
                <li>Library closed on 15th January (Maintenance Work).</li>
                <li>New Journals for Computer Science added.</li>
                <li>All pending fines must be cleared before semester exams.</li>
            </ul>
        `;
    }

    if (section === "digital") {
        box.innerHTML = `
            <h2>Digital Study Materials</h2>
            <ul>
                <li><a href="#">Java Programming PDF</a></li>
                <li><a href="#">Operating System Notes</a></li>
                <li><a href="#">DBMS Lecture Slides</a></li>
                <li><a href="#">Question Bank - Semester 4</a></li>
            </ul>
        `;
    }
}

function searchBook() {
    let query = document.getElementById('searchBook').value.toLowerCase();
    let result = document.getElementById('bookList');

    let books = [
        { name: "Java Programming", author: "E Balagurusamy" },
        { name: "Operating Systems", author: "Galvin" },
        { name: "DBMS", author: "Korth" },
        { name: "Computer Networks", author: "Tanenbaum" }
    ];

    let output = books
        .filter(book => book.name.toLowerCase().includes(query) || book.author.toLowerCase().includes(query))
        .map(book => `
            <div class="book-card">
                <h3>${book.name}</h3>
                <p>Author: ${book.author}</p>
            </div>
        `).join("");

    result.innerHTML = output || "<p>No matching books found.</p>";
}

function issueBook() {
    alert("Book Issued Successfully!");
}

function returnBook() {
    alert("Book Returned Successfully!");
}
