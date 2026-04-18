async function uploadZip() {
    document.getElementById("loader").classList.remove("loader-hidden");
    const fileInput = document.getElementById("zipFile");
    const file = fileInput.files[0];

    const formData = new FormData();
    formData.append("file", file);

    const response = await fetch("/upload", {
        method: "POST",
        body: formData
    });

    const data = await response.json();

    const tableBody = document.getElementById("resultTable");

    // Clear old results (important)
    tableBody.innerHTML = "";
    const summaryMap = {};

    // Build summary
    data.forEach(r => {
        const f1 = r.file1;
        const f2 = r.file2;
        const sim = r.similarity;

        if (!summaryMap[f1] || summaryMap[f1].similarity < sim) {
            summaryMap[f1] = {
                file: f2,
                similarity: sim
            };
        }

        if (!summaryMap[f2] || summaryMap[f2].similarity < sim) {
            summaryMap[f2] = {
                file: f1,
                similarity: sim
            };
        }
    });
    const summaryList = document.getElementById("summaryList");
    summaryList.innerHTML = "";
    for (const file in summaryMap) {
        const item = summaryMap[file];

        let className = "summary-low";

        if (item.similarity > 70) className = "summary-high";
        else if (item.similarity > 40) className = "summary-medium";

        const div = `
    <div class="summary-item ${className}">
        <strong>${file}</strong> ↔ <strong>${item.file}</strong><br>
        Similarity: ${item.similarity.toFixed(2)}%
    </div>
    `;

        summaryList.innerHTML += div;
    }
    data.forEach(r => {

        const similarity = r.similarity;
        const className = getSimilarityClass(similarity);

        let row;

        if (similarity > 80) {
            row = `
        <tr style="background-color: #faeaea;">
            <td>${r.file1}</td>
            <td>${r.file2}</td>
            <td class="${className}">${similarity.toFixed(2)}%</td>
        </tr>
        `;
        } else {
            row = `
        <tr>
            <td>${r.file1}</td>
            <td>${r.file2}</td>
            <td class="${className}">${similarity.toFixed(2)}%</td>
        </tr>
        `;
        }

        tableBody.innerHTML += row;
    });
    document.getElementById("loader").classList.add("loader-hidden");
}

function getSimilarityClass(value) {
    if (value > 70) return "similarity-high";
    if (value > 40) return "similarity-medium";
    return "similarity-low";
}