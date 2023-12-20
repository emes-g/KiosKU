package konkuk.swarchitecture.team6;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class KioskView extends JFrame {
	Kiosk k;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	// 싱글톤
	private static KioskView instance;

	public static KioskView getInstance() {
		if (instance == null)
			instance = new KioskView();
		return instance;
	}

	public KioskView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 429);
		setTitle("KiosKU");
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		setLocationRelativeTo(null); // 화면 중앙에 표시
		setVisible(true);
	}

	public String getPW(JFrame parent) {
		setKiosk(Kiosk.getInstance());

		JPasswordField passwordField = new JPasswordField();

		Object[] message = {"비밀번호를 입력하세요:", passwordField};
		passwordField.requestFocus();
		int option = JOptionPane.showConfirmDialog(parent, message, "비밀번호 입력", JOptionPane.DEFAULT_OPTION);

		if (option == JOptionPane.OK_OPTION)
			// getPassword 메서드는 char[]을 반환하므로 String으로 변환
			return new String(passwordField.getPassword());
		return null;
	}

	public int[] inputCurrency() {
		JTextField[] coinFields = new JTextField[Kiosk.CURRENCY_NUM];

		Object[] message = new Object[18];

		message[0] = "화폐 단위별 개수를 입력하세요:";

		for (int i=0; i<Kiosk.CURRENCY_NUM; i++) {
			coinFields[i] = new JTextField(5);
			message[i * 2 + 1] = Kiosk.UNITS[i] + "원:";
			message[i * 2 + 2] = coinFields[i];
		}
		coinFields[0].requestFocus();
		int option = JOptionPane.showConfirmDialog(null, message, "화폐 단위별 개수 입력", JOptionPane.DEFAULT_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			int[] coinCounts = new int[Kiosk.CURRENCY_NUM];
			for (int i=0; i<Kiosk.CURRENCY_NUM; i++) {
				try {
					coinCounts[i] = Integer.parseInt(coinFields[i].getText());
					if(coinCounts[i] < 0) {
						JOptionPane.showMessageDialog(null, "양수를 입력해주세요.");
						return null;
					}
				} catch (NumberFormatException e) {
					if(coinFields[i].getText().length() == 0) {
						coinCounts[i] = 0;
						continue;
					}
					JOptionPane.showMessageDialog(null, "숫자를 입력해주세요.");
					return null;
				}
			}
			return coinCounts;
		} 
		else 
			return null;
	}

	public void showMessagePopup(String message, String title) {
		JOptionPane.showMessageDialog(null, strToHtml(message), title, JOptionPane.PLAIN_MESSAGE);
	}

	public int selectForHereOrToGo() {
		// 매장, 포장 여부를 나타내는 버튼 배열
		Object[] options = {"매장", "포장"};

		// 팝업 창을 통해 사용자에게 선택지를 제공하고, 선택한 옵션에 따라 true 또는 false 반환
		int choice = JOptionPane.showOptionDialog(
				null,
				"매장 또는 포장을 선택해주세요.",
				"주문 옵션",
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[0]); // 기본 선택은 매장
	
		// 취소 -1, 매장 0, 포장 1
		return choice;
	}

	public void mainWindow() {
		setKiosk(Kiosk.getInstance());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("주문하기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				k.order();
			}
		});
		btnNewButton.setBounds(253, 184, 93, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("관리자 모드");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				k.setItem();
			}
		});
		btnNewButton_1.setFont(new Font("굴림", Font.PLAIN, 11));
		btnNewButton_1.setBounds(485, 361, 93, 23);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel = new JLabel("KiosKU");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 24));
		lblNewLabel.setBounds(253, 47, 93, 48);
		contentPane.add(lblNewLabel);

		setLocationRelativeTo(null); // 화면 중앙에 표시
		setVisible(true);

	}

	public void orderWindow(OrderManager om, Order o) {
		contentPane = new JPanel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel productListPanel = new JPanel();
		productListPanel.setLayout(new BoxLayout(productListPanel, BoxLayout.Y_AXIS));
		JScrollPane productListScrollPane = new JScrollPane(productListPanel);
		productListScrollPane.setBounds(10, 10, 300, 374);
		productListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(productListScrollPane);

		JPanel basketPanel = new JPanel();
		basketPanel.setLayout(new BoxLayout(basketPanel, BoxLayout.Y_AXIS));
		JScrollPane basketScrollPane = new JScrollPane(basketPanel);
		basketScrollPane.setBounds(320, 10, 258, 295);
		basketScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(basketScrollPane);

		JPanel totalPricePanel = new JPanel();
		totalPricePanel.setBounds(320, 315, 258, 30);
		totalPricePanel.setBackground(new Color(255, 255, 0));
		totalPricePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(totalPricePanel);

		ArrayList<Item> itemList = om.getItemManager().getItemList();

		for(int i=0; i<itemList.size(); i++) {
			Item item = itemList.get(i);

			JPanel itemPanel = createItemPanel(basketPanel, totalPricePanel, o, item);

			productListPanel.add(itemPanel);
		}

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(320, 351, 126, 33);
		contentPane.add(panel_2);

		JButton cancelBtn = new JButton("취소");
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainWindow();
			}
		});
		panel_2.add(cancelBtn);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(456, 351, 122, 33);
		contentPane.add(panel_3);

		JButton payBtn = new JButton("결제");
		payBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(o.getTotalPrice() > 0) {
					om.getOrderList().add(o);
					k.pay();
				}
			}
		});
		panel_3.add(payBtn);

		setLocationRelativeTo(null); // 화면 중앙에 표시
		setVisible(true);
	}

	public JPanel createItemPanel(JPanel basketPanel, JPanel totalPricePanel, Order o, Item item) {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());

		JLabel lblNewLabel = new JLabel(item.getTitle());
		panel.add(lblNewLabel);
		JLabel lblNewLabel_1 = new JLabel("(" + item.getCost()+"원)");
		panel.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("+");
		btnNewButton.setBackground(new Color(0, 255, 0));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				o.addItemToBasket(item, 1);
				updateBasket(basketPanel, o);
				updateTotalPrice(totalPricePanel, o);
			}
		});
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("-");
		btnNewButton_1.setBackground(new Color(255, 0, 0));
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				o.deleteItemFromBasket(item, 1);
				updateBasket(basketPanel, o);
				updateTotalPrice(totalPricePanel, o);
			}
		});
		panel.add(btnNewButton_1);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		return panel;
	}

	public void updateBasket(JPanel basketPanel, Order o) {
		basketPanel.removeAll();
		ArrayList<Pair<Item, Integer>> basket = o.getBasket();
		for(int i=0; i<basket.size(); i++) {
			Item item = basket.get(i).getX();
			int cnt = basket.get(i).getY();

			if(cnt > 0) {
				JPanel panel = new JPanel();
				panel.setLayout(new FlowLayout());

				JLabel label = new JLabel(item.getTitle() + " X " + cnt + " : " + item.getCost() * cnt + "원");
				panel.add(label);
				panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				basketPanel.add(panel);	
			}
		}

		basketPanel.revalidate();
		basketPanel.repaint();
	}

	public void updateTotalPrice(JPanel totalPridePanel, Order o) {
		totalPridePanel.removeAll();
		JLabel label = new JLabel("총 금액 : " + o.getTotalPrice() + "원");
		totalPridePanel.add(label);
		totalPridePanel.revalidate();
		totalPridePanel.repaint();
	}

	public int inputHeadcount() {
		String inputValue = null;
		boolean validInput = false;

		while (!validInput) {
			inputValue = JOptionPane.showInputDialog("결제 인원 수를 입력하세요:");

			if (inputValue == null)
				// 사용자가 취소 버튼을 눌렀을 때
				return -1;

			try {
				// 유효한 숫자인지 확인
				int numPeople = Integer.parseInt(inputValue);
				if (numPeople <= 0)
					JOptionPane.showMessageDialog(null, "양수를 입력하세요.", "오류", JOptionPane.ERROR_MESSAGE);
				else if (numPeople <= Kiosk.MAX_HEADCOUNT)
					validInput = true;
				else
					JOptionPane.showMessageDialog(null, "최대 " + Kiosk.MAX_HEADCOUNT + "명까지 가능합니다.", "오류", JOptionPane.ERROR_MESSAGE);
			} catch (NumberFormatException e) {
				// 숫자로 변환할 수 없는 경우
				JOptionPane.showMessageDialog(null, "숫자를 입력하세요.", "오류", JOptionPane.ERROR_MESSAGE);
			}
		}

		return Integer.parseInt(inputValue);
	}

	public String selectPaymentMethod(int idx) {
		Object[] options = {"현금", "카드"};
		int selectedOption = JOptionPane.showOptionDialog(
				null,
				"결제 방식을 선택하세요:",
				"결제자 " + idx + " - 결제 방식 선택",
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[0]);

		if (selectedOption == 0)
			return "현금";
		else if (selectedOption == 1)
			return "카드";
		else
			// 사용자가 취소 버튼을 눌렀거나 유효하지 않은 입력
			return null;
	}

	public void showPaymentInfo(int headcount, int[] prices) {
		StringBuilder message = new StringBuilder();
		message.append("결제자 수: ").append(headcount).append("명\n\n");

		for (int i=0; i<headcount; i++)
			message.append("결제자 ").append(i + 1).append(": ").append(prices[i]).append("원\n");

		JOptionPane.showMessageDialog(null, message.toString(), "결제 정보", JOptionPane.INFORMATION_MESSAGE);
	}


	public String inputCardID() {
		JPasswordField[] passwordFields = new JPasswordField[4];
		JPanel panel = new JPanel(new FlowLayout());
		int option = -1;
		
		for (int i = 0; i < passwordFields.length; i++) {
			passwordFields[i] = new JPasswordField(4);
			passwordFields[i].addKeyListener(new NumberInputKeyListener());
			panel.add(passwordFields[i]);
		}

		while(option == -1)
			option = JOptionPane.showConfirmDialog(null, panel, "카드번호 입력", JOptionPane.DEFAULT_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			// getPassword 메서드는 char[]을 반환하므로 String으로 변환
			String cardID = "";
			for (int i = 0; i < 4; i++) {
				cardID += new String(passwordFields[i].getPassword());
			}	
			return cardID;
		} 
		else
			return null;
	}

	// 숫자만 입력 가능하도록 하는 KeyListener 클래스
	static class NumberInputKeyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
			char c = e.getKeyChar();
			if (!Character.isDigit(c) || ((JTextField) e.getSource()).getText().length() >= 4) {
				e.consume(); // 입력 무시
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// 사용하지 않음
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// 사용하지 않음
		}
	}

	public void displayCurrency(Currency c, String message, String title) {
		// count 배열에 저장된 동전 개수를 JLabel로 추가
		String str = "";
		str += (message + "\n");
		for (int i=0; i<c.getUnits().length; i++) {
			str += (Kiosk.UNITS[i] + "원 X " + c.getUnits()[i] + " 개\n");
		}
		str += ("총 금액 : " + c.getTotal() + "원");

		// 팝업창 표시
		JOptionPane.showConfirmDialog(
				null,
				strToHtml(str),
				title,
				JOptionPane.DEFAULT_OPTION
				);
	}

	public void displayReceipt(Receipt receipt, int num) {
		// 팝업창 표시
		JOptionPane.showMessageDialog(
				null,
				strToHtml(receipt.getReceiptInfo()),
				"결제자 " + num + "님의 영수증",
				JOptionPane.DEFAULT_OPTION
				);

	}

	public String strToHtml(String str) {
		return "<html><body><p>" + str.replaceAll("\n", "<br>") + "</p></body></html>";
	}

	public void completedWindow(int orderNum) {		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("주문번호");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel.setBackground(new Color(240, 240, 240));
		lblNewLabel.setBounds(245, 45, 98, 31);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(Integer.toString(orderNum));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 60));
		lblNewLabel_1.setBounds(55, 114, 485, 149);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("이용해주셔서 감사합니다!");
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(186, 304, 234, 39);
		contentPane.add(lblNewLabel_2);

		setLocationRelativeTo(null); // 화면 중앙에 표시
		setVisible(true);

		Timer timer = new Timer(5000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 타이머가 호출되면 실행될 코드
				mainWindow();     
			}
		});
		timer.setRepeats(false); // 타이머는 한 번만 실행
		timer.start();		
	}

	public void itemWindow(ItemManager im) {	
		DefaultListModel<String> productList;
		JList<String> productListUI;
		JTextField titleField;
		JTextField priceField;

		contentPane = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.setBounds(100, 100, 600, 429);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		ArrayList<Item> itemList = im.getItemList();

		productList = new DefaultListModel<>();
		for(int i = 0; i < itemList.size(); i++) {
			productList.addElement(itemList.get(i).getTitle() + " - " + itemList.get(i).getCost() + "원");
		}

		JPanel inputPanel = new JPanel(new GridLayout(3, 2));
		inputPanel.add(new JLabel(" 상품명 :"));
		titleField = new JTextField();
		inputPanel.add(titleField);
		inputPanel.add(new JLabel(" 가격 :"));
		priceField = new JTextField();
		inputPanel.add(priceField);

		productListUI = new JList<>(productList);
		productListUI.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		productListUI.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				updateFieldsFromSelection(productListUI, titleField, priceField);
			}
		});

		JScrollPane scrollPane = new JScrollPane(productListUI);

		JButton addButton = new JButton("추가");
		addButton.setBackground(new Color(0, 255, 0));
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addProduct(productList, productListUI, titleField, priceField, im);
			}
		});

		JButton editButton = new JButton("수정");
		editButton.setBackground(new Color(255, 255, 0));
		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateProduct(productList, productListUI, titleField, priceField, im);
			}
		});

		JButton deleteButton = new JButton("삭제");
		deleteButton.setBackground(new Color(255, 0, 0));
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteProduct(productList, productListUI, titleField, priceField, im);
			}
		});

		JButton completeButton = new JButton("완료");
		completeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainWindow();
			}
		});

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(completeButton);

		contentPane.setLayout(new BorderLayout());
		contentPane.add(scrollPane, BorderLayout.CENTER);
		contentPane.add(inputPanel, BorderLayout.SOUTH);
		contentPane.add(buttonPanel, BorderLayout.EAST);

		setLocationRelativeTo(null); // 화면 중앙에 표시
		setVisible(true);
	}

	private void updateFieldsFromSelection(JList<String> productListUI, JTextField titleField,JTextField priceField) {
		String selectedValue = productListUI.getSelectedValue();
		if (selectedValue != null) {
			String[] parts = selectedValue.split(" - ");
			if (parts.length == 2) {
				titleField.setText(parts[0]);
				priceField.setText(parts[1].substring(0, parts[1].length() - 1)); // Remove the dollar sign
			}
		}
	}

	private void addProduct(DefaultListModel<String> productList, JList<String> productListUI, 
			JTextField titleField,JTextField priceField, ItemManager im) {
		String title = titleField.getText();
		String priceStr = priceField.getText();
		
		if (!title.isEmpty() && !priceStr.isEmpty()) {
			try {
				int price = Integer.parseInt(priceStr);
				
				if(price <= 0)
					showMessagePopup("상품 가격은 0원 이하일 수 없습니다.", "추가 실패");
				else if(im.addItem(title, price)) {
					productList.addElement(title + " - " + price + "원");
					clearFields(productListUI, titleField, priceField);
				}
				else
					showMessagePopup("동일한 상품명이 존재합니다.", "추가 실패");				
			}catch (NumberFormatException nfe) {
				showMessagePopup("상품 가격을 숫자로 입력해주세요.", "추가 실패");
			}
		}
	}

	private void updateProduct(DefaultListModel<String> productList, JList<String> productListUI, 
			JTextField titleField,JTextField priceField, ItemManager im) {
		int selectedIndex = productListUI.getSelectedIndex();
		
		if (selectedIndex != -1) {
			String title = titleField.getText();
			String priceStr = priceField.getText();
			
			if (!title.isEmpty() && !priceStr.isEmpty()) {
				try {
					int price = Integer.parseInt(priceStr);
					
					if(price <= 0)
						showMessagePopup("상품 가격은 0원 이하일 수 없습니다.", "수정 실패");
					else if(im.editItem(title, price, selectedIndex)) {
						productList.setElementAt(title + " - " + price + "원", selectedIndex);
						clearFields(productListUI, titleField, priceField);
					}
					else
						showMessagePopup("동일한 상품명이 존재합니다.", "수정 실패");
				}catch (NumberFormatException nfe) {
					showMessagePopup("상품 가격을 숫자로 입력해주세요.", "수정 실패");
				}
			}
		}
	}

	private void deleteProduct(DefaultListModel<String> productList, JList<String> productListUI, 
			JTextField titleField,JTextField priceField, ItemManager im) {
		int selectedIndex = productListUI.getSelectedIndex();
		if (selectedIndex != -1) {
			im.deleteItem(selectedIndex);
			productList.remove(selectedIndex);
			clearFields(productListUI, titleField, priceField);
		}
	}

	private void clearFields(JList<String> productListUI, JTextField titleField, JTextField priceField) {
		titleField.setText("");
		priceField.setText("");
		productListUI.clearSelection();
	}

	public void setKiosk(Kiosk k) {
		this.k= k;
	}
}