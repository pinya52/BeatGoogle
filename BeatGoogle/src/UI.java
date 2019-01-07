import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class UI {

 KeywordList list = new KeywordList();
 ArrayList<Node> nodeList = new ArrayList<Node>();
 Sorter sorter;

 static JFrame frame = new JFrame();
 JPanel panel = new JPanel();
 JButton addButton = new JButton("add");
 JButton startButton = new JButton("Start");
 JLabel imageLabel = new JLabel("想要更多圖片嗎？");
 JLabel keywordLabel = new JLabel("輸入關鍵字與權重(1~5)");
 JLayeredPane layeredPane = new JLayeredPane();
 JLabel a = new JLabel("");
 JTextField keywordText = new JTextField();
 JTextField weightText = new JTextField();
 JRadioButton yesButton = new JRadioButton("Yes");
 JRadioButton noButton = new JRadioButton("No");
 ButtonGroup group = new ButtonGroup();
 JPanel panel_1 = new JPanel();
 JTextArea relativeArea = new JTextArea();;
 JTextArea resultArea = new JTextArea();
 JTextArea keywordListArea = new JTextArea();
 JLabel resultLabel = new JLabel("搜尋結果");
 JLabel keywordListLabel = new JLabel("關鍵字清單");
 JLabel relativeLabel = new JLabel("相關關鍵字");
 JButton restartButton = new JButton("restart");

 public static void main(String[] args) {

  UI gui = new UI();
  gui.run();
  frame.setVisible(true);

 }

 public void run() {

  // set frame
  frame.getContentPane().setBackground(new Color(230, 230, 250));
  frame.getContentPane().setForeground(new Color(102, 102, 255));
  frame.setBounds(100, 100, 975, 800);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setBackground(new Color(102, 153, 255));
  frame.getContentPane().setLayout(null);

  // set panel
  frame.getContentPane().add(panel);
  panel.setBackground(new Color(230, 230, 250));
  panel.setBounds(224, 162, 465, 113);
  frame.getContentPane().add(panel_1);
  panel_1.setBackground(new Color(230, 230, 250));
  panel_1.setBounds(230, 26, 459, 124);

  // set keyword label
  keywordLabel.setBounds(136, 237, 118, 16);
  panel.add(keywordLabel);

  // set keyword text
  keywordText.setBounds(131, 256, 230, 26);
  keywordText.setColumns(10);
  panel.add(keywordText);

  // set weight text
  weightText.setBounds(367, 256, 26, 26);
  weightText.setColumns(2);
  panel.add(weightText);

  // set add button
  addButton.setBounds(426, 259, 133, 23);
  addButton.addActionListener(new addButtonListener());
  panel.add(addButton);

  // set image label
  imageLabel.setBounds(175, 294, 112, 16);
  panel.add(imageLabel);

  // set yes button
  yesButton.setBounds(146, 334, 70, 23);
  panel.add(yesButton);
  // yesButton.addItemListener(new ButtonListener());

  // set no button
  noButton.setBounds(304, 334, 70, 23);
  panel.add(noButton);
  // noButton.addItemListener(new ButtonListener());

  group.add(yesButton);
  group.add(noButton);

  // set start button
  startButton.setBounds(426, 318, 133, 29);
  panel.add(startButton);
  startButton.addActionListener(new startButtonListener());

  // set image
  ImageIcon img = new ImageIcon("src/2 下午3.17.52.jpg");
  img.setImage(img.getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT));
  a.setBackground(new Color(255, 255, 255));
  a.setIcon(img);
  panel_1.add(a);

  relativeArea.setBackground(new Color(230, 230, 250));
  relativeArea.setBounds(177, 733, 654, 37);
  frame.getContentPane().add(relativeArea);
  relativeArea.setColumns(10);

  resultLabel.setBounds(101, 286, 52, 16);
  frame.getContentPane().add(resultLabel);

  keywordListLabel.setBounds(743, 26, 76, 16);
  frame.getContentPane().add(keywordListLabel);

  relativeLabel.setBounds(77, 733, 76, 16);
  frame.getContentPane().add(relativeLabel);

  resultArea.setBackground(new Color(230, 230, 250));
  resultArea.setBounds(177, 286, 639, 425);
  frame.getContentPane().add(resultArea);

  keywordListArea.setBackground(new Color(230, 230, 250));
  keywordListArea.setBounds(743, 42, 180, 233);
  frame.getContentPane().add(keywordListArea);

  // set Area Text field
  resultArea.setEditable(false);
  keywordListArea.setEditable(false);
  relativeArea.setEditable(false);

  // set scroll pane
  JScrollPane scrollPane = new JScrollPane(resultArea);
  scrollPane.setBounds(177, 286, 639, 425);
  frame.getContentPane().add(scrollPane);
  scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

  // set restart button
  restartButton.setBounds(865, 733, 104, 29);
  frame.getContentPane().add(restartButton);
  restartButton.setVisible(false);
  restartButton.addActionListener(new restartButtonListener());

 }

 class addButtonListener implements ActionListener {

  public void actionPerformed(ActionEvent e) {
   String keyword = keywordText.getText();
   String weightS = weightText.getText();
   try {
    double weight = Double.parseDouble(weightS);
    Keyword k = new Keyword(keyword, weight);
    list.add(k);
    keywordListArea.append(k.toString() + "\n");
    keywordText.setText(null);
    weightText.setText(null);
   } catch (Exception ee) {
    keywordText.setText(null);
    weightText.setText(null);

   }

  }
 }

 class startButtonListener implements ActionListener {

  public void actionPerformed(ActionEvent e) {

   resultArea.append("Loading...");

   try {
    HashMap<String, String> result = list.getSearchEngineResult();

    for (Entry<String, String> re : result.entrySet()) {
     String url = re.getValue();
     String name = re.getKey();
     Node node = new Node(url, list, name);
     node.addChildren();
     // node.setNodeScore();
     // node.setImageScore();

     // if (node.totalScore != 0)
     nodeList.add(node);
    }

    sorter = new Sorter(nodeList);
    sorter.rank();

    for (int i = 0; i < nodeList.size(); i++) {

     Node node = nodeList.get(i);
     resultArea.append("Name: " + node.getName() + "\n");
     resultArea.append("URL: " + node.getURL() + "\n");
     if (yesButton.isSelected())
      resultArea.append("Score: " + node.getTotalScore() + "\n");
     else
      resultArea.append("Score: " + node.getNodeScore() + "\n");
    }

    ArrayList<String> relativeKeyword = new ArrayList<String>();
    relativeKeyword = list.getRelativeKeyword();
    for (int i = 0; i < relativeKeyword.size(); i++) {
     if (i == 4)
      relativeArea.append(relativeKeyword.get(i) + " " + "\n");
     else
      relativeArea.append(relativeKeyword.get(i) + " ");

    }
   }

   catch (IOException ex) {
   }

   restartButton.setVisible(true);
   keywordText.setEditable(false);
   weightText.setEditable(false);

  }
 }

 class restartButtonListener implements ActionListener {

  public void actionPerformed(ActionEvent e) {

   list = new KeywordList();
   nodeList = new ArrayList<Node>();
   keywordListArea.setText(null);
   resultArea.setText(null);
   relativeArea.setText(null);
   restartButton.setVisible(false);
   keywordText.setEditable(true);
   weightText.setEditable(true);

  }

 }
}