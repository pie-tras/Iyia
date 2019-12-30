package com.draglantix.entites;

import org.joml.Vector2f;

import com.draglantix.flare.graphics.Graphics;
import com.draglantix.flare.textures.Animation;
import com.draglantix.flare.util.Functions;
import com.draglantix.main.Assets;
import com.draglantix.states.PlayState;
import com.draglantix.world.WorldConfig;

public class Ethuzhi extends Dynamic{

	private Player player;
	
	private float speed = 7f;
	
	private Vector2f last = new Vector2f(), toPlayer = new Vector2f();
	
	private float width, height;
	
	public Ethuzhi(Animation animation, Vector2f position, Vector2f scale, Player player, Graphics g) {
		super(animation, position, scale, new Vector2f(8, 4));
		this.player = player;
		width = WorldConfig.WORLD_SIZE.x * WorldConfig.TILE_SIZE.x;
		height = WorldConfig.WORLD_SIZE.y * WorldConfig.TILE_SIZE.y;
	}
	
	@Override
	public void tick() {
		super.tick();
		
		toPlayer = new Vector2f(player.getPosition()).sub(position).normalize(speed);
		
		if(PlayState.lightBar != 0) {
			if(position.distance(player.getPosition()) < 200) {
				position.add(toPlayer);
				if(position.distance(player.getPosition()) < 60) {
					position = new Vector2f((Functions.rand.nextFloat() * width) - width/2, (Functions.rand.nextFloat() * height) - height/2);
					source.play(Assets.scream);
				}
			}
		}else {
			position.add(toPlayer);
			if(position.distance(player.getPosition()) < 10) {
				PlayState.dead = true;
				source.play(Assets.scream);
			}
		}
		
		last = toPlayer;
		handleAnimations();
	}
	
	private void handleAnimations() {
		
		if(PlayState.lightBar != 0 && position.distance(player.getPosition()) >= 200) {
			if(last.y > 0) {
				this.animation = Assets.IenemyUAnim;
			} else if(last.y < 0) {
				this.animation = Assets.IenemyDAnim;
			}

			if(last.x > 0) {
				this.animation = Assets.IenemyRAnim;
			} else if(last.x < 0) {
				this.animation = Assets.IenemyLAnim;
			}
		}else {
			if(toPlayer.y > 0) {
				this.animation = Assets.enemyUAnim;
			} else if(toPlayer.y < 0) {
				this.animation = Assets.enemyDAnim;
			}
	
			if(toPlayer.x > 0) {
				this.animation = Assets.enemyRAnim;
			} else if(toPlayer.x < 0) {
				this.animation = Assets.enemyLAnim;
			}
		}
		
	}
	

}
