function uploadZip(){

    const fileInput = document.getElementById("zipFile");

    const file = fileInput.files[0];

    if(!file){
        alert("Please select a file first");
        return;
    }

    const formData = new FormData();

    formData.append("file", file);

    fetch("/upload", {
        method: "POST",
        body: formData
    })
    .then(response => response.text())
    .then(data => {
        document.getElementById("result").innerText = data;
    })
    .catch(error => {
        console.error(error);
    });

}