package tn.esprit.spring.payload.request;

import javax.validation.constraints.NotBlank;

public class ResponseReclamationRequest {
	@NotBlank
    private String reclamationId;

	@NotBlank
	private String message;

	public String getReclamationId() {
		return reclamationId;
	}

	public void setReclamationId(String reclamationId) {
		this.reclamationId = reclamationId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
