package it.uniroma3.diadia.ambienti;

public enum Direzioni {
	
	
	nord (){
	@Override public Direzioni opposta() {
		return sud;
	}		
	},
	sud(){
		@Override public Direzioni opposta() {
			return nord;
		}		
	},
	ovest(){
		@Override public Direzioni opposta() {
			return est;
		}		
	},
	est(){
		@Override public Direzioni opposta() {
			return ovest;
		}
	};

	public abstract Direzioni opposta();



}
