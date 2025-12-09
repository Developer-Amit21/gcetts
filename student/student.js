// student js 
function openSection(section) {
    let content = document.getElementById("content");

    if (section === "profile") {
        content.innerHTML = `
            <h2>My Profile</h2>
            <p>Name: Amit Shaw</p>
            <p>Course: B.Tech</p>
            <p>Roll No: 21CSE124</p>
        `;
    }

    if (section === "courses") {
        content.innerHTML = `
            <h2>My Courses</h2>
            <ul>
                <li>Data Structures</li>
                <li>Operating Systems</li>
                <li>DBMS</li>
                <li>Java Programming</li>
            </ul>
        `;
    }

    if (section === "attendance") {
        content.innerHTML = `
            <h2>Attendance</h2>
            <p>Overall Attendance: 82%</p>
        `;
    }

    if (section === "notes") {
        content.innerHTML = `
            <h2>Download Notes</h2>
            <ul>
                <li><a href="#">DSA Notes</a></li>
                <li><a href="#">DBMS Notes</a></li>
            </ul>
        `;
    }

    if (section === "exam") {
        content.innerHTML = `
            <h2>Exam Details</h2>
            <p>Mid-Term: 20 Feb 2025</p>
            <p>Final Exam: 10 May 2025</p>
        `;
    }
}
