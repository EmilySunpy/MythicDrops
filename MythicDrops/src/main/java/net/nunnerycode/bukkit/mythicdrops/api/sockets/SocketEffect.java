package net.nunnerycode.bukkit.mythicdrops.api.sockets;

import org.bukkit.entity.LivingEntity;

public interface SocketEffect {

	int getIntensity();

	int getDuration();

	EffectTarget getEffectTarget();

	int getRadius();

	boolean isAffectsWielder();

	boolean isAffectsTarget();

	void apply(LivingEntity target);

}
