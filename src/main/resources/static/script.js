function showTab(tab) {
    document.querySelectorAll(".tab").forEach(t => t.classList.remove("active"));
    document.getElementById(tab).classList.add("active");
}

async function runDeadlock() {

    let input = document.getElementById("edgesInput").value;

    if (!input.trim()) {
        alert("Please enter process dependencies!");
        return;
    }

    try {
        const edges = JSON.parse(input);

        const res = await fetch("/api/detect", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ edges })
        });

        const data = await res.json();
        document.getElementById("deadlockResult").innerText = data.message;

    } catch (err) {
        document.getElementById("deadlockResult").innerText =
            "❌ Invalid JSON format!";
    }
}

async function runBanker() {

    let input = document.getElementById("bankerInput").value;

    if (!input.trim()) {
        alert("Please enter banker data!");
        return;
    }

    try {
        const dataInput = JSON.parse(input);

        const res = await fetch("/api/banker", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(dataInput)
        });

        const text = await res.text();
        document.getElementById("bankerResult").innerText = text;

    } catch (err) {
        document.getElementById("bankerResult").innerText =
            "❌ Invalid JSON format!";
    }
}