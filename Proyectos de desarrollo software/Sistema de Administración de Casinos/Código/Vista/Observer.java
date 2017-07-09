//
//  Created by Jonathan Carrero.
//  Copyright (c) Jonathan Carrero. All rights reserved.
//
package Vista;

public interface Observer {
	/** @param juego, @param mesa */
	void onReset(String juego, int mesa);
	/** */
	void onCambioJuego();
}
