package konkuk.swarchitecture.team6;

import java.util.ArrayList;

public class CardCompany {
	private static ArrayList<CardInformation> CardDatabase = new ArrayList<CardInformation>();
	
	public static boolean checkPayable(String insertedCardID, int price) {		
		for (CardInformation cardInfo : CardDatabase) {
		    if(insertedCardID.equals(cardInfo.getCardID())) {
		    	if(cardInfo.getCardLimit() >= cardInfo.getCurrentUsed() + cardInfo.getPreAuthorized() + price) {
		    		cardInfo.setPreAuthorized(cardInfo.getPreAuthorized() + price);
		    		return true;
		    	}
		    	return false;
		    }
		}
		return false;
	}
	
	public static void cancelPreAuthorization(String insertedCardID, int price) {
		for (CardInformation cardInfo : CardDatabase) {
		    if(insertedCardID.equals(cardInfo.getCardID())) {
		    	cardInfo.setPreAuthorized(cardInfo.getPreAuthorized() - price);
		    	return;
		    }
		}
	}
	
	public static void changeCardData(String insertedCardID, int price) {
		for (CardInformation cardInfo : CardDatabase) {
			// 실승인에 따른 가승인 제거
		    if(insertedCardID.equals(cardInfo.getCardID())) {
		    	cardInfo.setCurrentUsed(cardInfo.getCurrentUsed() + price);
		    	cardInfo.setPreAuthorized(cardInfo.getPreAuthorized() - price);
		    }
		}
	}

	public static ArrayList<CardInformation> getCardDatabase() {
		return CardDatabase;
	}
}
