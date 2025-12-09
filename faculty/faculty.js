// faculty js 
function loadSection(section) {
    let box = document.getElementById('content');

    if (section === "departments") {
        box.innerHTML = `
            <h2>Departments</h2>
            <div class="card"><h3>Computer Science & Engineering</h3><p>HOD: Dr. A. Banerjee</p></div>
            <div class="card"><h3>Electronics & Communication</h3><p>HOD: Dr. R. Sen</p></div>
            <div class="card"><h3>Electrical Engineering</h3><p>HOD: Prof. S. Roy</p></div>
            <div class="card"><h3>Civil Engineering</h3><p>HOD: Dr. P. Das</p></div>
            <div class="card"><h3>Mechanical Engineering</h3><p>HOD: Prof. T. Gupta</p></div>
        `;
    }

    if (section === "faculty") {
        box.innerHTML = `
            <h2>Faculty Profiles</h2>

            <div class="card">
                <h3>Dr. Ananya Banerjee</h3>
                <p>CSE Dept | Qualification: PhD (AI)</p>
                <p>Experience: 12 Years</p>
            </div>

            <div class="card">
                <h3>Prof. Rahul Sen</h3>
                <p>ECE Dept | Qualification: M.Tech (VLSI)</p>
                <p>Experience: 9 Years</p>
            </div>

            <div class="card">
                <h3>Dr. Priya Das</h3>
                <p>CE Dept | Qualification: PhD (Structures)</p>
                <p>Experience: 15 Years</p>
            </div>

            <div class="card">
                <h3>Prof. Soumik Roy</h3>
                <p>EE Dept | Qualification: M.Tech (Power Systems)</p>
                <p>Experience: 10 Years</p>
            </div>
        `;
    }

    if (section === "timetable") {
        box.innerHTML = `
            <h2>Department Timetables</h2>
            <ul>
                <li><a href="#">CSE – Semester Timetable</a></li>
                <li><a href="#">ECE – Department Timetable</a></li>
                <li><a href="#">ME – Workshop Schedule</a></li>
                <li><a href="#">EE – Lab Timetable</a></li>
            </ul>
        `;
    }

    if (section === "research") {
        box.innerHTML = `
            <h2>Research & Publications</h2>
            <ul>
                <li>AI Research Paper – Dr. A Banerjee (2024)</li>
                <li>Signal Processing Journal – Prof. Sen</li>
                <li>Structural Engineering Publication – Dr. Das</li>
                <li>Power Systems Optimization – Prof. Roy</li>
            </ul>
        `;
    }

    if (section === "notices") {
        box.innerHTML = `
            <h2>Faculty Notices</h2>
            <ul>
                <li>Staff Meeting: 18th January 2025</li>
                <li>Workshop on AI Tools for Teaching – Next Week</li>
                <li>Internal Evaluation Submission Deadline: 25th January</li>
            </ul>
        `;
    }
}
