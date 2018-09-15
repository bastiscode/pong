package de.game.pong.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.game.pong.builder.BallComponent;
import de.game.pong.builder.PaddleComponent;

public class Helper {

	private static final Logger LOGGER = LogManager.getLogger();

	public static int checkCollision(BallComponent ball, PaddleComponent paddle) {
		int collisionIndicator = 0; // 0 -> no collision, 1 -> collision, 2 -> collision at side of paddle
		int[] ballStats = ball.getStats();
		int[] paddleStats = paddle.getStats();
		int nearestX = Math.max(paddleStats[0] - paddleStats[2] / 2,
				Math.min(ballStats[0], paddleStats[0] + paddleStats[2] / 2));
		int nearestY = Math.max(paddleStats[1] - paddleStats[3] / 2,
				Math.min(ballStats[1], paddleStats[1] + paddleStats[3] / 2));
		int deltaX = ballStats[0] - nearestX;
		int deltaY = ballStats[1] - nearestY;

		// check for collision
		if ((deltaX * deltaX + deltaY * deltaY) < (ballStats[2] * ballStats[2])) {
			collisionIndicator = 1;
			LOGGER.debug("collision detected");
			// check for collision at side of paddle
			if ((ballStats[0] >= paddleStats[0] + paddleStats[2] / 2)
					|| (ballStats[0] <= paddleStats[0] - paddleStats[2] / 2)) {
				collisionIndicator = 2;
				LOGGER.debug("collision was at side of paddle");
			}
		}

		return collisionIndicator;
	}
}
