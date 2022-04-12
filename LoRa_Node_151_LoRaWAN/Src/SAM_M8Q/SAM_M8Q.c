/*
 * SAM_M8Q.c
 *
 *  Created on: Aug 26, 2021
 *      Author: Praktikant
 */

#include "SAM_M8Q.h"

union u_Short uShort;
union i_Short iShort;
union u_Long uLong;
union i_Long iLong;

bool is_UPD_SOS_ACK = false;
bool is_UPD_SOS_RESTORED = false;
/*!
 * Structure initialization.
 * @param GNSS Pointer to main GNSS structure.
 * @param huart Pointer to uart handle.
 */
void GNSS_Init(GNSS_StateHandle *GNSS, UART_HandleTypeDef *huart) {
	GNSS->huart = huart;
	GNSS->year = 0;
	GNSS->month = 0;
	GNSS->day = 0;
	GNSS->hour = 0;
	GNSS->min = 0;
	GNSS->sec = 0;
	GNSS->fixType = 0;
	GNSS->flags = 0;
	GNSS->numSV = 0;
	GNSS->lon = 0;
	GNSS->lat = 0;
	GNSS->height = 0;
	GNSS->hMSL = 0;
	GNSS->hAcc = 0;
	GNSS->vAcc = 0;
	GNSS->gSpeed = 0;
	GNSS->headMot = 0;
}

void GNSSDataReset(GNSS_StateHandle *GNSS) {
	GNSS->year = 0;
	GNSS->month = 0;
	GNSS->day = 0;
	GNSS->hour = 0;
	GNSS->min = 0;
	GNSS->sec = 0;
	GNSS->fixType = 0;
	GNSS->flags = 0;
	GNSS->numSV = 0;
	GNSS->lon = 0;
	GNSS->lat = 0;
	GNSS->lonBytes[0] = 0;
	GNSS->lonBytes[1] = 0;
	GNSS->lonBytes[2] = 0;
	GNSS->lonBytes[3] = 0;
	GNSS->latBytes[0] = 0;
	GNSS->latBytes[1] = 0;
	GNSS->latBytes[2] = 0;
	GNSS->latBytes[3] = 0;
	GNSS->fLon = 0;
	GNSS->fLat = 0;
	GNSS->height = 0;
	GNSS->hMSL = 0;
	GNSS->hAcc = 0;
	GNSS->vAcc = 0;
	GNSS->gSpeed = 0;
	GNSS->headMot = 0;
}

/*!
 * Searching for a header in data buffer and matching class and message ID to buffer data.
 * @param GNSS Pointer to main GNSS structure.
 */
void GNSS_ParseBuffer(GNSS_StateHandle *GNSS) {

	for (int var = 0; var <= 100; ++var) {
		if (GNSS->uartWorkingBuffer[var] == 0xB5
				&& GNSS->uartWorkingBuffer[var + 1] == 0x62) {
			if (GNSS->uartWorkingBuffer[var + 2] == 0x27
					&& GNSS->uartWorkingBuffer[var + 3] == 0x03) { //Look at: 32.19.1.1 u-blox 8 Receiver description
//				GNSS_ParseUniqID(GNSS);
			} else if (GNSS->uartWorkingBuffer[var + 2] == 0x01
					&& GNSS->uartWorkingBuffer[var + 3] == 0x21) { //Look at: 32.17.14.1 u-blox 8 Receiver description
//				GNSS_ParseNavigatorData(GNSS);
			} else if (GNSS->uartWorkingBuffer[var + 2] == 0x01
					&& GNSS->uartWorkingBuffer[var + 3] == 0x07) { //ook at: 32.17.30.1 u-blox 8 Receiver description
				GNSS_ParsePVTData(GNSS, var);
			} else if (GNSS->uartWorkingBuffer[var + 2] == 0x01
					&& GNSS->uartWorkingBuffer[var + 3] == 0x02) { // Look at: 32.17.15.1 u-blox 8 Receiver description
				GNSS_ParsePOSLLHData(GNSS, var);
			}
		}
	}
}
/*!
 * Make request for navigation position velocity time solution data.
 * @param GNSS Pointer to main GNSS structure.
 */
void GNSS_GetPVTData(GNSS_StateHandle *GNSS) {
	HAL_UART_Transmit_DMA(GNSS->huart, getPVTData,
			sizeof(getPVTData) / sizeof(uint8_t));

	while(HAL_UART_GetState(GNSS->huart)!=HAL_UART_STATE_READY)
	{

	}

	HAL_UART_Receive(GNSS->huart, GNSS_Handle.uartWorkingBuffer, 200, 1000);
}
/*!
 * Make request for navigation position velocity time solution data.
 * @param GNSS Pointer to main GNSS structure.
 */
void GNSS_GetPVTData_DMA(GNSS_StateHandle *GNSS) {
	HAL_UART_Transmit_DMA(GNSS->huart, getPVTData,
			sizeof(getPVTData) / sizeof(uint8_t));
/*
	while(HAL_UART_GetState(GNSS->huart)!=HAL_UART_STATE_READY)
	{

	}
*/

	HAL_UART_Receive_DMA(GNSS->huart, GNSS_Handle.uartWorkingBuffer, 200);

}

/*!
 * Make request for geodetic position solution data.
 * @param GNSS Pointer to main GNSS structure.
 */
void GNSS_GetPOSLLHData(GNSS_StateHandle *GNSS) {
	HAL_UART_Transmit_DMA(GNSS->huart, getPOSLLHData,
			sizeof(getPOSLLHData) / sizeof(uint8_t));

	HAL_UART_Receive(GNSS->huart, GNSS_Handle.uartWorkingBuffer, 36, 100);
}

void GNSS_GetPOSLLHData_DMA(GNSS_StateHandle *GNSS) {
	HAL_UART_Transmit_DMA(GNSS->huart, getPOSLLHData,
			sizeof(getPOSLLHData) / sizeof(uint8_t));

	HAL_UART_Receive_DMA(GNSS->huart, GNSS_Handle.uartWorkingBuffer, 200);
}

/*!
 * Parse data to navigation position velocity time solution standard.
 * Look at: 32.17.15.1 u-blox 8 Receiver description.
 * @param GNSS Pointer to main GNSS structure.
 */
void GNSS_ParsePVTData(GNSS_StateHandle *GNSS, int varStart) {
	uShort.bytes[0] = GNSS_Handle.uartWorkingBuffer[varStart+10];
	GNSS->yearBytes[0]=GNSS_Handle.uartWorkingBuffer[varStart+10];
	uShort.bytes[1] = GNSS_Handle.uartWorkingBuffer[varStart+11];
	GNSS->yearBytes[1]=GNSS_Handle.uartWorkingBuffer[varStart+11];
	GNSS->year = uShort.uShort;
	GNSS->month = GNSS_Handle.uartWorkingBuffer[varStart+12];
	GNSS->day = GNSS_Handle.uartWorkingBuffer[varStart+13];
	GNSS->hour = GNSS_Handle.uartWorkingBuffer[varStart+14];
	GNSS->min = GNSS_Handle.uartWorkingBuffer[varStart+15];
	GNSS->sec = GNSS_Handle.uartWorkingBuffer[varStart+16];
	GNSS->fixType = GNSS_Handle.uartWorkingBuffer[varStart+26];
	GNSS->flags = GNSS_Handle.uartWorkingBuffer[varStart+27];
	GNSS->numSV = GNSS_Handle.uartWorkingBuffer[varStart+28];

	for (int var = 0; var < 4; ++var) {
		iLong.bytes[var] = GNSS_Handle.uartWorkingBuffer[varStart+var + 30];
		GNSS->lonBytes[var]= GNSS_Handle.uartWorkingBuffer[varStart+var + 30];
	}
	GNSS->lon = iLong.iLong;
	GNSS->fLon=(float)iLong.iLong/10000000.0;
	for (int var = 0; var < 4; ++var) {
		iLong.bytes[var] = GNSS_Handle.uartWorkingBuffer[varStart+var + 34];
		GNSS->latBytes[var]=GNSS_Handle.uartWorkingBuffer[varStart+var + 34];
	}
	GNSS->lat = iLong.iLong;
	GNSS->fLat=(float)iLong.iLong/10000000.0;
	for (int var = 0; var < 4; ++var) {
		iLong.bytes[var] = GNSS_Handle.uartWorkingBuffer[varStart+var + 38];
	}
	GNSS->height = iLong.iLong;

	for (int var = 0; var < 4; ++var) {
		iLong.bytes[var] = GNSS_Handle.uartWorkingBuffer[varStart+var + 42];
		GNSS->hMSLBytes[var] = GNSS_Handle.uartWorkingBuffer[varStart+var + 42];
	}
	GNSS->hMSL = iLong.iLong;

	for (int var = 0; var < 4; ++var) {
		uLong.bytes[var] = GNSS_Handle.uartWorkingBuffer[varStart+var + 46];
	}
	GNSS->hAcc = uLong.uLong;

	for (int var = 0; var < 4; ++var) {
		uLong.bytes[var] = GNSS_Handle.uartWorkingBuffer[varStart+var + 50];
	}
	GNSS->vAcc = uLong.uLong;

	for (int var = 0; var < 4; ++var) {
		iLong.bytes[var] = GNSS_Handle.uartWorkingBuffer[varStart+var + 66];
		GNSS->gSpeedBytes[var] = GNSS_Handle.uartWorkingBuffer[varStart+var + 66];
	}
	GNSS->gSpeed = iLong.iLong;

	for (int var = 0; var < 4; ++var) {
		iLong.bytes[var] = GNSS_Handle.uartWorkingBuffer[varStart+var + 70];
	}
	GNSS->headMot = iLong.iLong * 1e-5; // todo I'm not sure this good options.
}

/*!
 * Parse data to geodetic position solution standard.
 * Look at: 32.17.14.1 u-blox 8 Receiver description.
 * @param GNSS Pointer to main GNSS structure.
 */
void GNSS_ParsePOSLLHData(GNSS_StateHandle *GNSS, int varStart) {
	for (int var = 0; var < 4; ++var) {
		iLong.bytes[var] = GNSS_Handle.uartWorkingBuffer[varStart+ var + 10];
	}
	GNSS->lon = iLong.iLong;
	GNSS->fLon=(float)iLong.iLong/10000000.0;

	for (int var = 0; var < 4; ++var) {
		iLong.bytes[var] = GNSS_Handle.uartWorkingBuffer[varStart+ var + 14];
	}
	GNSS->lat = iLong.iLong;
	GNSS->fLat=(float)iLong.iLong/10000000.0;

	for (int var = 0; var < 4; ++var) {
		iLong.bytes[var] = GNSS_Handle.uartWorkingBuffer[varStart+ var + 18];
	}
	GNSS->height = iLong.iLong;

	for (int var = 0; var < 4; ++var) {
		iLong.bytes[var] = GNSS_Handle.uartWorkingBuffer[varStart+ var + 22];
	}
	GNSS->hMSL = iLong.iLong;

	for (int var = 0; var < 4; ++var) {
		uLong.bytes[var] = GNSS_Handle.uartWorkingBuffer[varStart+ var + 26];
	}
	GNSS->hAcc = uLong.uLong;

	for (int var = 0; var < 4; ++var) {
		uLong.bytes[var] = GNSS_Handle.uartWorkingBuffer[varStart+ var + 30];
	}
	GNSS->vAcc = uLong.uLong;
}


void GNSS_LoadConfig(GNSS_StateHandle *GNSS) {

	HAL_UART_Transmit_DMA(GNSS->huart, configUBX,
			sizeof(configUBX) / sizeof(uint8_t));

	while(HAL_UART_GetState(GNSS->huart)!=HAL_UART_STATE_READY)
	{

	}

	HAL_UART_Receive(GNSS->huart, GNSS_Handle.uartWorkingBuffer, 200, 1000);


	HAL_UART_Transmit_DMA(GNSS->huart, setNMEA410,
			sizeof(setNMEA410) / sizeof(uint8_t));

	while(HAL_UART_GetState(GNSS->huart)!=HAL_UART_STATE_READY)
	{

	}

	HAL_UART_Receive(GNSS->huart, GNSS_Handle.uartWorkingBuffer, 200, 1000);




	HAL_UART_Transmit_DMA(GNSS->huart, setGNSS,
			sizeof(setGNSS) / sizeof(uint8_t));

	while(HAL_UART_GetState(GNSS->huart)!=HAL_UART_STATE_READY)
	{

	}

	HAL_UART_Receive(GNSS->huart, GNSS_Handle.uartWorkingBuffer, 100, 1000);


		HAL_UART_Transmit_DMA(GNSS->huart, UBXSaveSettings,
							sizeof(UBXSaveSettings) / sizeof(uint8_t));


		while(HAL_UART_GetState(GNSS->huart)!=HAL_UART_STATE_READY)
		{

		}
		HAL_UART_Receive(GNSS->huart, GNSS->uartWorkingBuffer, 100, 1000);

/*
	HAL_UART_Transmit_DMA(GNSS->huart, setPortableMode,
			sizeof(setPortableMode) / sizeof(uint8_t));

	while(HAL_UART_GetState(GNSS->huart)!=HAL_UART_STATE_READY)
	{

	}

	HAL_UART_Receive(GNSS->huart, GNSS_Handle.uartWorkingBuffer, 100, 1000);
*/
}


void GNSS_LoadPSMConfig(GNSS_StateHandle *GNSS) {

	HAL_UART_Transmit_DMA(GNSS->huart, setPowerMode,
						sizeof(setPowerMode) / sizeof(uint8_t));

	while(HAL_UART_GetState(GNSS->huart)!=HAL_UART_STATE_READY)
	{

	}
	HAL_UART_Receive(GNSS->huart, GNSS->uartWorkingBuffer, 200, 1000);


	HAL_UART_Transmit_DMA(GNSS->huart, setPowerModeSettingsPM2,
						sizeof(setPowerModeSettingsPM2) / sizeof(uint8_t));


	while(HAL_UART_GetState(GNSS->huart)!=HAL_UART_STATE_READY)
	{

	}
	HAL_UART_Receive(GNSS->huart, GNSS->uartWorkingBuffer, 200, 1000);


	/*
		HAL_UART_Transmit_DMA(GNSS->huart, setPMS,
							sizeof(setPMS) / sizeof(uint8_t));


		while(HAL_UART_GetState(GNSS->huart)!=HAL_UART_STATE_READY)
		{

		}
		HAL_UART_Receive(GNSS->huart, GNSS->uartWorkingBuffer, 100, 1000);

	*/
/*
	HAL_UART_Transmit_DMA(GNSS->huart, UBXSaveSettings,
						sizeof(UBXSaveSettings) / sizeof(uint8_t));


	while(HAL_UART_GetState(GNSS->huart)!=HAL_UART_STATE_READY)
	{

	}
	HAL_UART_Receive(GNSS->huart, GNSS->uartWorkingBuffer, 100, 1000);
*/
}



/*
void GNSSgetVersion(GNSS_StateHandle *GNSS){
		HAL_UART_Transmit_DMA(GNSS->huart, getUBXVersion,
					sizeof(getUBXVersion) / sizeof(uint8_t));

		while(HAL_UART_GetState(GNSS->huart)!=HAL_UART_STATE_READY)
		{

		}
		uint8_t buffer[300];
		HAL_UART_Receive(GNSS->huart, buffer, 300, 3000);

		char SWChar[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		char HWChar[] = {0,0,0,0,0,0,0,0,0,0};

		for(int i=6;i<36;i++){
			SWChar[i-6] = GNSS->uartWorkingBuffer[i];
		}

		for(int i=37;i<47;i++){
			HWChar[i-37] = GNSS->uartWorkingBuffer[i];
		}
}
*/
void GNSSColdStartReset(GNSS_StateHandle *GNSS){
		HAL_UART_Transmit_DMA(GNSS->huart, UBXColdStartReset,
					sizeof(UBXColdStartReset) / sizeof(uint8_t));

		while(HAL_UART_GetState(GNSS->huart)!=HAL_UART_STATE_READY)
		{

		}
}


