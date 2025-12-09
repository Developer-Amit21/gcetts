// teacher js 
function showSection(section) {
    let content = document.getElementById("content");

    if (section === "staff") {
        content.innerHTML = `
            <h2>Teaching Staff List</h2>

            <div class="staff-card">
                <h3>Dr. A. K. Sharma</h3>
                <p>Principal</p>
            </div>

            <div class="staff-card">
                <h3>Prof. R. Mukherjee</h3>
                <p>HOD – Computer Science</p>
            </div>

            <div class="staff-card">
                <h3>Prof. P. Roy</h3>
                <p>Professor – Mathematics</p>
            </div>

            <div class="staff-card">
                <h3>Ms. S. Das</h3>
                <p>Assistant Professor – IT</p>
            </div>
        `;
    }

    if (section === "subjects") {
        content.innerHTML = `
            <h2>Subjects Assigned</h2>
            <ul>
                <li>Java Programming</li>
                <li>Web Development</li>
                <li>Operating Systems</li>
                <li>Computer Networks</li>
            </ul>
        `;
    }

    if (section === "schedule") {
        content.innerHTML = `
            <h2>Weekly Class Schedule</h2>
            <ul>
                <li>Monday – Java (10 AM)</li>
                <li>Wednesday – OS (11 AM)</li>
                <li>Thursday – CN (12 PM)</li>
                <li>Friday – Web Dev (10 AM)</li>
            </ul>
        `;
    }

    if (section === "students") {
        content.innerHTML = `
            <h2>Manage Students</h2>
            <p>Total Students: 120</p>
            <button onclick="alert('Attendance Marked!')">Mark Attendance</button>
            <button onclick="alert('Internal Marks Submitted!')">Submit Marks</button>
        `;
    }

    if (section === "material") {
        content.innerHTML = `
            <h2>Upload Study Materials</h2>
            <input type="file" /><br><br>
            <button onclick="alert('File Uploaded Successfully!')">Upload</button>
        `;
    }
}
