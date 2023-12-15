package konkuk.swarchitecture.team6;

import java.util.Scanner;

public class PaymentByCard extends Payment {
	private Scanner scan = new Scanner(System.in);
	private String insertedCardID;
	
	public PaymentByCard(int price) {
		super(price);
		this.insertedCardID = "카드 정보없음\n";
	}

	public String getInsertedCardID() {
		return insertedCardID;
	}

	public void setInsertedCardID(String insertedCardID) {
		this.insertedCardID = insertedCardID;
	}

	public String getUserPaymentMethod() {
		String paymentMethod;
		
		System.out.printf("결제 수단 입력 (현금, 카드) : ");
		paymentMethod = scan.nextLine();
		
		return paymentMethod;
	}
	
	@Override
	public boolean payPreProcessing() {
		System.out.printf("카드 번호 (16자리) : ");
		insertedCardID = scan.nextLine();
		
		if(!CardCompany.checkPayable(insertedCardID, price)) {
			System.out.printf("해당 카드로는 결제로 불가능합니다.\n");
			return false;
		}
		System.out.printf("선승인 완료 | 카드 번호 : %s\n", insertedCardID);
		return true;
	}

	@Override
	public void pay() {
		CardCompany.changeCardData(insertedCardID, price);
		System.out.printf("결제 완료 | 카드 번호 : %s\n", insertedCardID);
	}

	@Override
	public void revert() {
		CardCompany.cancelPreAuthorization(insertedCardID, price);
		System.out.printf("가승인 취소 | 카드 번호 : %s\n", insertedCardID);
	}
}
