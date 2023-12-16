package konkuk.swarchitecture.team6;

// DTO(Data Transfer Object) Class
public class CardInformation {
	private String cardID;	// 카드 번호
	private int cardLimit;	// 카드 한도
	private int preAuthorized;	// 카드 가승인 금액
	private int currentUsed;	// 현재 사용액
	
	public CardInformation(String cardID, int cardLimit, int preAuthorized, int currentUsed) {
		this.cardID = cardID;
		this.cardLimit = cardLimit;
		this.preAuthorized = preAuthorized;
		this.currentUsed = currentUsed;
	}	
	public String getCardID() {
		return cardID;
	}
	public int getCardLimit() {
		return cardLimit;
	}
	public int getPreAuthorized() {
		return preAuthorized;
	}
	public void setPreAuthorized(int preAuthorized) {
		this.preAuthorized = preAuthorized;
	}
	public int getCurrentUsed() {
		return currentUsed;
	}
	public void setCurrentUsed(int currentUsed) {
		this.currentUsed = currentUsed;
	}
}
