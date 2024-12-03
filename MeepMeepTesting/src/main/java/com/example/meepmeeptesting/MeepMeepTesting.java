package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseMap;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(30, 30, 5.82005, 4.6494, 10.51)
                .build();
        //predefined poses/vectors
        Pose2d startPose = new Pose2d(-30.75, -58.25, Math.toRadians(180));

        Vector2d bucketVector = new Vector2d(-56, -55.25);

        Pose2d bucketPose = new Pose2d(-56, -55.25, Math.toRadians(-135));

        Pose2d fieldPose1 = new Pose2d(-50, -38.25, Math.toRadians(90));
        Pose2d fieldPose2 = new Pose2d(-45, -22.25, Math.toRadians(180));
        Pose2d fieldPose3 = new Pose2d(-53, -22.25, Math.toRadians(180));

        Pose2d parkPose = new Pose2d(-30, 6.75, Math.toRadians(180));

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(58.25, 0, Math.toRadians(-90)))

                .splineToLinearHeading(new Pose2d(-58.25, 0, Math.toRadians(90)), Math.toRadians(90))

                .waitSeconds(30)

                .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_LIGHT)
                .setDarkMode(false)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}