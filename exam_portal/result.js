// Fetch student result from backend API
async function getResult() {
    const roll = document.getElementById("roll").value.trim();

    if (!roll) {
        alert("Please enter a roll number!");
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/api/result?roll=${roll}`);
        const data = await response.json();

        let html = "";

        if (data.error) {
            html = `<p style='color:red; font-weight:bold;'>${data.error}</p>`;
        } else {
            const total = (data.mid1 || 0) + (data.mid2 || 0) + (data.final || 0);

            html = `
                <div class='result-card'>
                    <h3>Student Name: <span>${data.name}</span></h3>
                    <p><strong>Mid-Sem 1:</strong> ${data.mid1}</p>
                    <p><strong>Mid-Sem 2:</strong> ${data.mid2}</p>
                    <p><strong>Final Exam:</strong> ${data.final}</p>
                    <hr>
                    <h3>Total Marks: ${total} / 300</h3>
                </div>
            `;
        }

        document.getElementById("resultBox").innerHTML = html;

    } catch (error) {
        document.getElementById("resultBox").innerHTML =
            `<p style="color:red;">Error connecting to server. Make sure backend is running.</p>`;
        console.error("Fetch error:", error);
    }
}
