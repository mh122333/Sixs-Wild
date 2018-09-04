package game.entity;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * The class of accomplishment. The record of the level.
 * @author Yo Karita
 *
 */
public class Acc implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6179405081397672895L;
	protected String name;
	protected int level;
	protected int score;
	protected int stars;
	protected String date;
	protected final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

	/**
	 * Constructor for Accomplishment class.
	 * @param level the level
	 * @param score the score
	 * @param stars the number of stars
	 */
	public Acc(int level, int score, int stars){
		this.level = level;
		this.name = "Level" + level;
		this.score = score;
		this.stars = stars;
		this.date = dateFormat.format(Calendar.getInstance().getTime());		
	}

	/**
	 * Constructor for Board class.
	 * @param l level
	 */
	public Acc(Level l){
		this.level = l.getLevel();
		this.name = "Level" + l.getLevel();
		this.score = l.getBoard().getCurrentScore();
		this.stars = l.getStar();
		this.date = dateFormat.format(Calendar.getInstance().getTime());	
	}
	/**
	 * Get name
	 * @return String name
	 *
	 */
	public String getName(){
		return this.name;
	}
	/**
	 * Get score
	 * @return int score
	 *
	 */
	public int getScore(){
		return this.score;
	}
	/**
	 * Get level
	 * @return int level
	 *
	 */
	public int getLevel(){
		return this.level;
	}
	/**
	 * Get stars
	 * @return int number of stars
	 *
	 */
	public int getStars() {
		return this.stars;
	}

	/**
	 * Get date
	 * @return String date
	 *
	 */
	public String getDate() {
		return this.date;
	}
	/**
	 * Set date
	 * @param d date to be set
	 *
	 */
	public void setDate(String d) {
		this.date = d;
	}

	/**
	 * Get copy of an Acc
	 * @return Acc the copy of an Acc
	 *
	 */
	public Acc copy(){
		Acc new_acc = new Acc(this.level, this.score, this.stars);
		new_acc.setDate(date);
		return new_acc;
	}
	/**
	 * Update the score if the score is higher than the high score
	 * @param acc the accomplishment to update
	 * @return boolean if the method was executed correctly
	 *
	 */
	public boolean override(Acc acc){
		if(!this.name.equals(acc.getName())){return false;}
		
		// Only override when the score is higher
		if (acc.getScore() > this.score){
			this.level = acc.getLevel();
			this.name = acc.getName();
			this.score = acc.getScore();
			this.stars =  acc.getStars();
			this.date = dateFormat.format(Calendar.getInstance().getTime());	
		}
		return true;
	}
	/**
	 * Checks if 2 accomplishments are same
	 * @param o the accomplishment to compare
	 * @return boolean if they are same
	 *
	 */
	@Override
	public boolean equals(Object o){		
		// If name are same then two Acc are same to prevent multiple Acc for same level
		if (o instanceof Acc){
			Acc a = (Acc) o;
			return this.name.equals(a.getName());
		}
		else{
			return false;
		}
	}	

}
