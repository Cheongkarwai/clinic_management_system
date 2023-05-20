/**
 * 
 */

const form = document.getElementById("forgot_password_form");
const emailInput = document.getElementById("input_email_address");
const otpInput = document.getElementById("input_otp");
const otpRows = document.getElementsByClassName("otp_row");
const errorRow = document.getElementById("error_row");
const errorMessages = document.getElementById("error_messages");
const statusInput = document.getElementById("input_status");

form.addEventListener("submit", e => {
	e.preventDefault();

	if (statusInput.value === "send-otp") {
		fetch(`http://localhost:8080/clinic_management_system/auth/forgot-password?action=${statusInput.value}`, {
			method: "POST",
			body: JSON.stringify({
				"emailAddress": emailInput.value
			}),
			headers: {
				"Content-Type": "application/json",
			}
		}).then(response => {
			return response.json();
		})
			.then(data => {

				if (data.status_code === 200) {
					for (let i = 0; i < otpRows.length; i++) {
						otpRows[i].style.display = "block";
					}
					statusInput.value = "verify-otp";
					errorRow.style.display = "none";
				}
				else if (data.status_code === 422) {

					let errors = "";

					for (let i = 0; i < data.message.length; i++) {
						errors +=
							`<li class="error">
						${data.message[i]}
					</li>`;
					}
					errorMessages.innerHTML = errors;
					errorRow.style.display = "table-row";

				}

			})
			.catch(err => {
				console.log(err);
			});
	} else if (statusInput.value === "verify-otp") {
		fetch(`http://localhost:8080/clinic_management_system/auth/forgot-password?action=${statusInput.value}`, {
			method: "POST",
			body: JSON.stringify({
				"emailAddress": emailInput.value,
				"otp": otpInput.value
			}),
			headers: {
				"Content-Type": "application/json",
			}
		}).then(response => {
			return response.json();
		})
			.then(data => {

				if (data.status_code === 200) {
					errorRow.style.display = "none";
					console.log("Hi");
				}
				else if (data.status_code === 422) {
					errorMessages.innerHTML = `<li class="error">
						${data.message}
					</li>`;
					errorRow.style.display = "table-row";

				}

			})
			.catch(err => {
				console.log(err);
			});
	}
});