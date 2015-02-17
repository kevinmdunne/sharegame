package com.sharegame.pricing.engine;

import java.util.Scanner;

public class PricingEngine {
	
	private UpdatePricesJob job;
	private Thread updatePricesThread;
	private boolean started;
	
	private static PricingEngine INSTANCE;
	
	public static PricingEngine getInstance(){
		if(INSTANCE == null){
			INSTANCE = new PricingEngine();
		}
		return INSTANCE;
	}
	
	private PricingEngine(){
		this.started = false;
	}
	
	public void start(){
		if(!this.started){
			job = new UpdatePricesJob();
			updatePricesThread = new Thread(job);
			updatePricesThread.start();
			this.started = true;
		}
	}
	
	public void stop(){
		if(this.started){
			updatePricesThread.interrupt();
			this.started = false;
		}
	}
	
	public boolean isRunning(){
		return this.started;
	}
	
	public static void main(String[] args){
		System.out.println("Starting pricing engine");
		PricingEngine engine = new PricingEngine();
		engine.start();
		
		new Scanner(System.in).nextLine();
		System.out.println("Stopping pricing engine");
		engine.stop();
	}
}
