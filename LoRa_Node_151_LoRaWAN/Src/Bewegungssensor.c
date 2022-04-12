#include "Bewegungssensor.h"
bool BewegungInterruptAusgeloest;
bool WurdeBewegt;
bool WurdeBestaetigt;

void BewegungssensorInit()
{
	BewegungInterruptAusgeloest = false;
	WurdeBewegt = true;
}

void PruefeInterruptStatus()
{
	if(BewegungInterruptAusgeloest==true)
		{
			BewegungInterruptAusgeloest=false;
			WurdeBewegt=true;
			SetzeBewegungssensorInDenSchlaf();
			RtcEnterLowPowerStopMode( );
		}
}

void SetzeBewegungssensorInDenSchlaf()
{
	HAL_GPIO_WritePin(GPIOB, GPIO_PIN_14, GPIO_PIN_RESET);
}

void HoleDenBewegungssensorAusDemSchlaf()
{
	HAL_GPIO_WritePin(GPIOB, GPIO_PIN_14, GPIO_PIN_SET);
}


