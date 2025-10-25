/*
 * SPDX-License-Identifier: MIT
 * Inspired from: https://docs.oracle.com/javase/tutorial/essential/concurrency/deadlock.html (Oracle license: redistribution allowed with attribution)
 * Part: Correction d'un programme multi-threads
 * Section: Spécification d'un problème concurrent
 * Subsection: Problèmes de vivacité
 * Slide: Que fait ce programme ?
 */

package snippets.liveness;

public record Friend(String nom) {
	
	public synchronized void salute(Friend friend) {
		System.out.println(this.nom + " dit : \"je salue mon ami " + friend.nom + "\"");
		friend.answer(this);
	}
	
	public synchronized void answer(Friend friend) {
		System.out.println(this.nom + " dit : \"mon ami " + friend.nom + " m'a salué et je le salue en retour\"");
	}
	
	public static void main(String[] args) {

		final Friend alphonse = new Friend("Alphonse");
		final Friend gaston = new Friend("Gaston");

		new Thread(() -> alphonse.salute(gaston)).start();
		new Thread(() -> gaston.salute(alphonse)).start();
		
	}
	
}

