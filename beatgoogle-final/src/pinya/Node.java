package pinya;

import java.io.IOException;
import java.util.ArrayList;

public class Node {

	public Node parent;
	public ArrayList<Node> children;

	private String url;
	private HTMLHandler handler;
	private String name;

	public double nodeScore;
	public double imageScore;
	public double totalScore;
	private KeywordList keywords;

	public Node(String url, KeywordList keywords) {
		this.url = url;
		this.handler = new HTMLHandler(url);
		this.children = new ArrayList<Node>();
		this.keywords = keywords;
	}

	public Node(String url, KeywordList keywords, String name) {
		this.url = url;
		this.handler = new HTMLHandler(url);
		this.children = new ArrayList<Node>();
		this.keywords = keywords;
		this.name = name;
	}

	public void addChildren() throws IOException {

		ArrayList<String> subLinks = new ArrayList<String>();
		subLinks = handler.getSubLink();

		for (int i = 0; i < subLinks.size(); i++) {

			Node child = new Node(subLinks.get(i), keywords);
			child.parent = this;
			children.add(child);
		}

	}

	public void setNodeScore() throws IOException {

		this.nodeScore = 0;

		for (int i = 0; i < keywords.size(); i++) {

			try {
				Keyword keyword = keywords.get(i);
				this.nodeScore += handler.countKeyword(keyword.getName()) * keyword.getWeight();
			} catch (IOException e) {
			}
		}

		for (Node child : children) {

			for (int i = 0; i < keywords.size(); i++) {

				try {
					Keyword keyword = keywords.get(i);
					child.nodeScore += child.handler.countKeyword(keyword.getName()) * keyword.getWeight();
				} catch (IOException e) {
				}
			}
			this.nodeScore += child.nodeScore;
		}

		this.totalScore = nodeScore + imageScore;

	}

	public void setImageScore() throws IOException {

		try {
			this.imageScore = 0;
			imageScore += handler.countImage() * 0.05;
		} catch (IOException e) {
		}

		for (Node child : children) {

			try {

				child.imageScore += child.handler.countImage() * 0.05;
			} catch (IOException e) {
			}

			this.imageScore += child.imageScore;
		}

		this.totalScore = nodeScore + imageScore;
	}

	public double getNodeScore() {
		return nodeScore;
	}

	public double getImageScore() {
		return imageScore;
	}

	public double getTotalScore() {
		return totalScore;
	}

	public String getName() {
		return name;
	}

	public String getURL() {
		return url;
	}
}
