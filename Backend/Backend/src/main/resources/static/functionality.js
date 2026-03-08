async function uploadZip() {

    const fileInput = document.getElementById("zipFile");
    const file = fileInput.files[0];

    const formData = new FormData();
    formData.append("file", file);

    const response = await fetch("/upload", {
        method: "POST",
        body: formData
    });

    const data = await response.json();

    const table = document.querySelector("table");

    data.forEach(r => {

        const row = table.insertRow();

        row.insertCell(0).innerText = r.file1;
        row.insertCell(1).innerText = r.file2;
        row.insertCell(2).innerText = r.similarity.toFixed(2) + "%";

    });
}