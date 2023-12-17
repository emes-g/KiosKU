package konkuk.swarchitecture.team6;

import java.util.ArrayList;

public class CardCompany {
	private static ArrayList<CardInformation> CardDatabase = CardInformationDAO.getCardInfoFromCardDB();

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
				CardInformationDAO.updatePreAuthorized(insertedCardID, price, false);
				return;
			}
		}
	}

	public static void changeCardData(String insertedCardID, int price) {
		for (CardInformation cardInfo : CardDatabase) {
			if(insertedCardID.equals(cardInfo.getCardID())) {
				// 가승인 제거
				cardInfo.setPreAuthorized(cardInfo.getPreAuthorized() - price);
				CardInformationDAO.updatePreAuthorized(insertedCardID, price, false);
				// 실승인
				cardInfo.setCurrentUsed(cardInfo.getCurrentUsed() + price);
				CardInformationDAO.updateCurrentUsed(insertedCardID, price);
			}
		}
	}

	public static ArrayList<CardInformation> getCardDatabase() {
		return CardDatabase;
	}
}
