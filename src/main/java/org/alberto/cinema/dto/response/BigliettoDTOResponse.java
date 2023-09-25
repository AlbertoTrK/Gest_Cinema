package org.alberto.cinema.dto.response;

public class BigliettoDTOResponse {
	private LoginDTOResponse lDTOResp;
	private SpettacoloDTOResponse spDTOResp;
	
	public LoginDTOResponse getlDTOResp() {
		return lDTOResp;
	}

	public void setlDTOResp(LoginDTOResponse lDTOResp) {
		this.lDTOResp = lDTOResp;
	}

	public SpettacoloDTOResponse getSpDTOResp() {
		return spDTOResp;
	}

	public void setSpDTOResp(SpettacoloDTOResponse spDTOResp) {
		this.spDTOResp = spDTOResp;
	}

}
