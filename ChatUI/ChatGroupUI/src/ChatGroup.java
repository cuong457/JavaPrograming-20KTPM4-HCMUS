import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import java.awt.ComponentOrientation;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import javax.swing.BoxLayout;

public class ChatGroup extends JFrame {

	private JPanel contentPane;
	private JButton friendButton;
	private JTextField searchHistoryInput;
	private JTextField messageInput;
	private JTextField newGroupInput;
	private JTextField newMemberInput;
	private JTextField setAdminInput;
	private JTextField deleteMemberInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatGroup frame = new ChatGroup();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChatGroup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 688);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setPreferredSize(new Dimension(300, 10));
		contentPane.add(panel, BorderLayout.WEST);
		
		JScrollPane friendScrollPane = new JScrollPane();
		friendScrollPane.setBackground(new Color(255, 255, 255));
		friendScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		friendScrollPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		friendScrollPane.setPreferredSize(new Dimension(300, 460));
		panel.add(friendScrollPane);
		
		JLabel lblNewLabel_1 = new JLabel("Chat");
		lblNewLabel_1.setBackground(new Color(64, 128, 128));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		friendScrollPane.setColumnHeaderView(lblNewLabel_1);
		
		JPanel friendPanel = new JPanel();
		friendPanel.setPreferredSize(new Dimension(280, 2000));
		friendPanel.setBackground(new Color(255, 255, 255));
		friendScrollPane.setViewportView(friendPanel);
		friendPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		friendButton = new JButton("Bạn bè 1");
		friendButton.setHorizontalAlignment(SwingConstants.LEFT);
		friendButton.setMinimumSize(new Dimension(20, 21));
		friendButton.setMaximumSize(new Dimension(20, 21));
		friendButton.setIcon(new ImageIcon("E:\\java - dh\\final project\\ChatPrivateUI\\src\\user2.png"));
		friendButton.setBackground(new Color(0, 255, 255));
		friendButton.setHorizontalTextPosition(SwingConstants.RIGHT);
		friendButton.setPreferredSize(new Dimension(270, 80));
		friendButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		friendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		friendPanel.add(friendButton);
		
		JButton btnNewButton_1 = new JButton("Bạn bè 1");
		btnNewButton_1.setPreferredSize(new Dimension(270, 80));
		btnNewButton_1.setMinimumSize(new Dimension(20, 21));
		btnNewButton_1.setMaximumSize(new Dimension(20, 21));
		btnNewButton_1.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBackground(Color.CYAN);
		friendPanel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Bạn bè 1");
		btnNewButton_2.setPreferredSize(new Dimension(270, 80));
		btnNewButton_2.setMinimumSize(new Dimension(20, 21));
		btnNewButton_2.setMaximumSize(new Dimension(20, 21));
		btnNewButton_2.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnNewButton_2.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2.setBackground(Color.CYAN);
		friendPanel.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("Bạn bè 1");
		btnNewButton_2_1.setPreferredSize(new Dimension(270, 80));
		btnNewButton_2_1.setMinimumSize(new Dimension(20, 21));
		btnNewButton_2_1.setMaximumSize(new Dimension(20, 21));
		btnNewButton_2_1.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnNewButton_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2_1.setBackground(Color.CYAN);
		friendPanel.add(btnNewButton_2_1);
		
		JButton btnNewButton_2_2 = new JButton("Bạn bè 1");
		btnNewButton_2_2.setPreferredSize(new Dimension(270, 80));
		btnNewButton_2_2.setMinimumSize(new Dimension(20, 21));
		btnNewButton_2_2.setMaximumSize(new Dimension(20, 21));
		btnNewButton_2_2.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnNewButton_2_2.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_2_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2_2.setBackground(Color.CYAN);
		friendPanel.add(btnNewButton_2_2);
		
		JButton btnNewButton_2_1_1 = new JButton("Bạn bè 1");
		btnNewButton_2_1_1.setPreferredSize(new Dimension(270, 80));
		btnNewButton_2_1_1.setMinimumSize(new Dimension(20, 21));
		btnNewButton_2_1_1.setMaximumSize(new Dimension(20, 21));
		btnNewButton_2_1_1.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnNewButton_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2_1_1.setBackground(Color.CYAN);
		friendPanel.add(btnNewButton_2_1_1);
		
		JButton btnNewButton_2_1_1_1 = new JButton("Bạn bè 1");
		btnNewButton_2_1_1_1.setPreferredSize(new Dimension(270, 80));
		btnNewButton_2_1_1_1.setMinimumSize(new Dimension(20, 21));
		btnNewButton_2_1_1_1.setMaximumSize(new Dimension(20, 21));
		btnNewButton_2_1_1_1.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnNewButton_2_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2_1_1_1.setBackground(Color.CYAN);
		friendPanel.add(btnNewButton_2_1_1_1);
		
		JPanel functionPanel = new JPanel();
		functionPanel.setPreferredSize(new Dimension(280, 160));
		panel.add(functionPanel);
		
		JButton viewListFriendButton = new JButton("Danh sách bạn bè");
		viewListFriendButton.setBackground(new Color(0, 255, 255));
		viewListFriendButton.setHorizontalTextPosition(SwingConstants.CENTER);
		viewListFriendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		functionPanel.setLayout(new GridLayout(0, 2, 0, 0));
		functionPanel.add(viewListFriendButton);
		
		JButton createGroupButton = new JButton("Tạo nhóm");
		createGroupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		createGroupButton.setBackground(new Color(0, 255, 255));
		functionPanel.add(createGroupButton);
		
		JButton btnNewButton_5 = new JButton("New button");
		btnNewButton_5.setBackground(new Color(0, 255, 255));
		functionPanel.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("New button");
		btnNewButton_6.setBackground(new Color(0, 255, 255));
		functionPanel.add(btnNewButton_6);
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(650, 10));
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_5.add(scrollPane_1);
		
		JTextArea chatArea = new JTextArea();
		chatArea.setPreferredSize(new Dimension(5, 420));
		scrollPane_1.setViewportView(chatArea);
		
		JLabel currentChatFriend = new JLabel("Bạn bè 1");
		currentChatFriend.setIcon(new ImageIcon("E:\\java - dh\\final project\\ChatPrivateUI\\src\\user2.png"));
		currentChatFriend.setFont(new Font("Tahoma", Font.BOLD, 16));
		currentChatFriend.setPreferredSize(new Dimension(43, 60));
		panel_5.add(currentChatFriend, BorderLayout.NORTH);
		
		JPanel panel_3 = new JPanel();
		panel_5.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		messageInput = new JTextField();
		panel_3.add(messageInput);
		messageInput.setColumns(10);
		
		JButton sendMessageButton = new JButton("Gửi tin");
		sendMessageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sendMessageButton.setBackground(new Color(0, 255, 255));
		panel_3.add(sendMessageButton, BorderLayout.EAST);
		
		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6, BorderLayout.CENTER);
		
		JButton deleteHistoryButton = new JButton("Xóa lịch sử chat");
		deleteHistoryButton.setBackground(new Color(0, 255, 255));
		deleteHistoryButton.setBounds(23, 20, 137, 31);
		deleteHistoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_6.setLayout(null);
		panel_6.add(deleteHistoryButton);
		
		searchHistoryInput = new JTextField();
		searchHistoryInput.setBounds(23, 84, 327, 31);
		searchHistoryInput.setPreferredSize(new Dimension(2000, 30));
		panel_6.add(searchHistoryInput);
		searchHistoryInput.setColumns(10);
		
		JButton sendSearchHistoryButton = new JButton("Tìm");
		sendSearchHistoryButton.setBackground(new Color(0, 128, 192));
		sendSearchHistoryButton.setForeground(new Color(255, 255, 255));
		sendSearchHistoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sendSearchHistoryButton.setBounds(362, 84, 74, 27);
		panel_6.add(sendSearchHistoryButton);
		
		JButton deleteHistoryButton2 = new JButton("Xóa lịch sử chat2");
		deleteHistoryButton2.setBackground(new Color(0, 255, 255));
		deleteHistoryButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		deleteHistoryButton2.setBounds(170, 20, 136, 31);
		panel_6.add(deleteHistoryButton2);
		
		JLabel lblNewLabel = new JLabel("Tìm kiếm");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(23, 61, 79, 13);
		panel_6.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(300, 10));
		panel_2.setMaximumSize(new Dimension(300, 32767));
		contentPane.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblTnNhmMi = new JLabel("Tên nhóm mới");
		lblTnNhmMi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(lblTnNhmMi);
		
		JLabel label = new JLabel("");
		panel_2.add(label);
		
		newGroupInput = new JTextField();
		newGroupInput.setPreferredSize(new Dimension(2000, 30));
		newGroupInput.setColumns(10);
		panel_2.add(newGroupInput);
		
		JLabel label_1 = new JLabel("");
		panel_2.add(label_1);
		
		JButton newGroupNameButton = new JButton("Đổi");
		newGroupNameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		newGroupNameButton.setForeground(Color.WHITE);
		newGroupNameButton.setBackground(new Color(0, 128, 192));
		panel_2.add(newGroupNameButton);
		
		JLabel lblThnhVinMi = new JLabel("Thành viên mới");
		lblThnhVinMi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(lblThnhVinMi);
		
		JLabel label_2 = new JLabel("");
		panel_2.add(label_2);
		
		newMemberInput = new JTextField();
		newMemberInput.setPreferredSize(new Dimension(2000, 30));
		newMemberInput.setColumns(10);
		panel_2.add(newMemberInput);
		
		JLabel label_3 = new JLabel("");
		panel_2.add(label_3);
		
		JButton newMemberButton = new JButton("Thêm");
		newMemberButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		newMemberButton.setForeground(Color.WHITE);
		newMemberButton.setBackground(new Color(0, 128, 192));
		panel_2.add(newMemberButton);
		
		JLabel lblGnAdmin = new JLabel("Gán quyền admin");
		lblGnAdmin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(lblGnAdmin);
		
		JLabel label_4 = new JLabel("");
		panel_2.add(label_4);
		
		JButton setAdminButton = new JButton("Gán");
		setAdminButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		setAdminInput = new JTextField();
		setAdminInput.setPreferredSize(new Dimension(2000, 30));
		setAdminInput.setColumns(10);
		panel_2.add(setAdminInput);
		setAdminButton.setForeground(Color.WHITE);
		setAdminButton.setBackground(new Color(0, 128, 192));
		panel_2.add(setAdminButton);
		
		JLabel lblXaThnhVin = new JLabel("Xóa thành viên");
		lblXaThnhVin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(lblXaThnhVin);
		
		deleteMemberInput = new JTextField();
		deleteMemberInput.setPreferredSize(new Dimension(2000, 30));
		deleteMemberInput.setColumns(10);
		panel_2.add(deleteMemberInput);
		
		JButton deleteMemberButton = new JButton("Xóa");
		deleteMemberButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		deleteMemberButton.setForeground(Color.WHITE);
		deleteMemberButton.setBackground(new Color(0, 128, 192));
		panel_2.add(deleteMemberButton);
		
		JLabel label_5 = new JLabel("");
		panel_2.add(label_5);
	}
}
