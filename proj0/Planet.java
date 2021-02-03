public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	private static double G=6.67e-11;
	public String imgFileName;
	public Planet(double xP, double yP, double xV,
		double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Planet(Planet p){
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}
	public double calcDistance(Planet rocinate){
		return Math.sqrt((rocinate.xxPos-this.xxPos)*(rocinate.xxPos-this.xxPos)
			+(rocinate.yyPos-this.yyPos)*(rocinate.yyPos-this.yyPos));
	}
	public double calcForceExertedBy(Planet rocinate){
		double F;
		F=G*this.mass*rocinate.mass/this.calcDistance(rocinate)/this.calcDistance(rocinate);
		return F;
	}
	public double calcForceExertedByX(Planet rocinate){
		return calcForceExertedBy(rocinate)/calcDistance(rocinate)*(rocinate.xxPos-xxPos);
	}
	public double calcForceExertedByY(Planet rocinate){
		return calcForceExertedBy(rocinate)/calcDistance(rocinate)*(rocinate.yyPos-yyPos);
	}
	private boolean equals(Planet rocinate){
		if(xxPos == rocinate.xxPos && yyPos == rocinate.yyPos){
			return true;
		}else{
			return false;
		}
	}
	public double calcNetForceExertedByX(Planet [] allPlanets){
		double xsum = 0;
		for(int i = 0;i < allPlanets.length ; i++ ){
			if(equals(allPlanets[i]) == false){
				xsum += calcForceExertedByX(allPlanets[i]);
			}
		}
		return xsum;
	}
	public double calcNetForceExertedByY(Planet [] allPlanets){
		double ysum = 0;
		for(int i = 0;i < allPlanets.length ; i++ ){
			if(equals(allPlanets[i]) == false){
				ysum += calcForceExertedByY(allPlanets[i]);
			}
		}
		return ysum;
	}
	public void update(double dt, double xforce, double yforce){
		xxVel += xforce / mass * dt;
		yyVel += yforce / mass * dt;
		xxPos += dt * xxVel;
		yyPos += dt * yyVel;
	}
	public void draw(){
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}
}