/*
 * SAM_M8Q.h
 *
 *  Created on: Aug 26, 2021
 *      Author: Praktikant
 */

#ifndef SAM_M8Q_SAM_M8Q_H_
#define SAM_M8Q_SAM_M8Q_H_

#include "main.h"
#include <stdint.h>
#include <stdbool.h>

union u_Short
{
	uint8_t bytes[2];
	unsigned short uShort;
};

union i_Short
{
	uint8_t bytes[2];
	signed short iShort;
};

union u_Long
{
	uint8_t bytes[4];
	unsigned long uLong;
};

union i_Long
{
	uint8_t bytes[4];
	signed long iLong;
};

typedef struct
{
	UART_HandleTypeDef *huart;

	uint8_t uniqueID[4];
	uint8_t uartWorkingBuffer[201];

	unsigned short year;
	uint8_t yearBytes[2];
	uint8_t month;
	uint8_t day;
	uint8_t hour;
	uint8_t min;
	uint8_t sec;
	uint8_t fixType;
	uint8_t flags;
	uint8_t numSV;

	signed long lon;
	uint8_t lonBytes[4];
	signed long lat;
	uint8_t latBytes[4];
	float fLon;
	float fLat;

	signed long height;
	signed long hMSL;
	uint8_t hMSLBytes[4];
	unsigned long hAcc;
	unsigned long vAcc;

	signed long gSpeed;
	uint8_t gSpeedBytes[4];
	signed long headMot;

}GNSS_StateHandle;

GNSS_StateHandle GNSS_Handle;

//32.10.25 UBX-CFG-PRT (0x06 0x00)
//settings for UART communication including port, baud rate, parity, stop bits
static const uint8_t configUBX[]={0xB5,0x62,0x06,0x00,0x14,0x00,							//Header, Klasse, Länge
										0x01,	//port id
										0x00,	//Reserviert
										0x00,0x00,	//tx ready pin Konfiguration
										0xC0,0x08,0x00,0x00,	//UART mode, 1 stop bit, no parity, 8bit Länge
										0x80,0x25,0x00,0x00,	//Baudrate, 9600
										0x01,0x00,				//inprotomask, UBX in
										0x01,0x00,				//outprotomask, UBX out
										0x00,0x00,				//Flaggen
										0x00,0x00,				//Reserviert
										0x8A,0x79};				//Checksummen

//32.10.21 UBX-CFG-NMEA (0x06 0x17)
//nmea version + settings
static const uint8_t setNMEA410[]={0xB5,0x62,0x06,0x17,0x14,0x00,							//Header, Klasse, Länge
										0x00,	//Flaggen für Filter, 2 = Weitergabe invalid Position fix
										0x41,	//NMEA-Version 4.10
										0x00,	//Nummer der SV, 0 = unlimited
										0x02,	//Flaggen, 2 = Considering Mode -> alle erfassten Satelliten für Fix verwenden
										0x00,0x00,0x00,0x00,	//Filter von Satellitensystemen
										0x00,					//SVnumbering, off
										0x00,					//MainTalkerID, nicht überschrieben
										0x00,					//gsvTalkerID, nutze NMEA-definierte ID
										0x01,					//Nachrichtenversion
										0x00,0x00,				//bdsTalkerID
										0x00,0x00,0x00,0x00,0x00,0x00,	//Reserviert
										0x75,0x57};											//Checksummen

//32.10.13 UBX-CFG-GNSS (0x06 0x3E)
//Activation of navigation system: Galileo, Glonass, GPS, SBAS
static const uint8_t setGNSS[]={0xB5,0x62,0x06,0x3E,0x24,0x00,								//Header, Klasse, Länge
									0x00,		//Nachrichtenversion
									0x00,		//Read-Only Anzahl der Tracking-Kanäle
									0x20,		//Anzahl der zu verwendenden Tracking-Kanäle, 32
									0x04,		//Anzahl folgenden Konfigurationsblöcke

									0x00,		//GNSS-ID -> GPS
									0x08,		//Anzahl reservierter Tracking-Kanäle
									0x10,		//Maximale Anzahl der zu verwendenden Tracking-Kanäle
									0x00,		//Reserviert
									0x01,0x00,0x01,0x00,	//Flaggen, Enable, Version

									0x01,		//GNSS-ID ->SBAS
									0x01,
									0x03,
									0x00,
									0x01,0x00,0x01,0x00,

									0x02,		//GNSS-ID -> Galilio
									0x04,
									0x08,
									0x00,
									0x01,0x00,0x01,0x00,

									0x06,		//GNSS-ID -> GLONASS
									0x08,
									0x0E,
									0x00,
									0x01,0x00,0x01,0x00,
									0xDB,0xC7};												//Checksummen


//32.10.19 UBX-CFG-NAV5 (0x06 0x24)
static const uint8_t setPortableMode[]={0xB5,0x62,0x06,0x24,0x24,0x00,
		0xFF,0xFF,				//Flaggen, welche Änderungen durchgenommen werden, alle
		0x00,					//Modus, portable
		0x03,					//fixModus, auto 2D/3D
		0x00,0x00,0x00,0x00,	//fixed Altitude, wenn 2D-Modus feste Höhe
		0x10,0x27,0x00,0x00,	//fixed Altitude Varianz, Varianz für Höhe
		0x05,					//Minimale Höhe für Satelliten, 5°
		0x00,					//drLimit, reserviert
		0xFA,0x00,				//Positionmaske
		0xFA,0x00,				//Time DoP Maske
		0x64,0x00,				//Postion Accuracy Maske
		0x5E,0x01,				//Time Accuracy Maske
		0x00,					//Static Hold Threshold
		0x3C,					//DGNSS Timeout
		0x00,					//
		0x00,					//
		0x00,0x00,				//Reserviert
		0x00,0x00,				//Static hold max Distance
		0x00,					//UTC Standard, automatisch
		0x00,0x00,0x00,0x00,0x00,	//Reserviert
		0x7E,0x3C};


//32.17.17 UBX-NAV-PVT (0x01 0x07)
//Postion Velocity Time Request
static const uint8_t getPVTData[]={0xB5,0x62,0x01,0x07,0x00,0x00,0x08,0x19};				//Header, Klasse, Länge, Checksummen

//32.17.16 UBX-NAV-POSLLH (0x01 0x02)
static const uint8_t getPOSLLHData[]={0xB5,0x62,0x01,0x02,0x00,0x00,0x03,0x0A};				//Header, Klasse, Länge, Checksummen


//32.10.3 UBX-CFG-CFG (0x06 0x09)
static const uint8_t UBXSaveSettings[] = {0xB5, 0x62, 0x06, 0x09, 0x0C, 0x00,				//Header, Klasse, Länge
											0x00, 0x00, 0x00, 0x00,
											0x19, 0x00, 0x00, 0x00,
											0x00, 0x00, 0x00, 0x00,
											0x34, 0x57};									//Checksummen

static const uint8_t UBXLoadSettings[] = {0xB5, 0x62, 0x06, 0x09, 0x0C, 0x00,				//Header, Klasse, Länge
											0x00, 0x00, 0x00, 0x00,							//Clear
											0x00, 0x00, 0x00, 0x00,							//Save
											0x19, 0x00, 0x00, 0x00,							//Load
											0x34, 0xF3};									//Checksummen

static const uint8_t UBXClearSettings[] = {0xB5, 0x62, 0x06, 0x09, 0x0C, 0x00,				//Header, Klasse, Länge
											0x19, 0x00, 0x00, 0x00,							//Clear
											0x00, 0x00, 0x00, 0x00,							//Save
											0x00, 0x00, 0x00, 0x00,							//Load
											0x34, 0xBB};									//Checksummen

//32.10.29 UBX-CFG-RST (0x06 0x04)
static const uint8_t UBXReset[] = {0xB5, 0x62, 0x06, 0x04, 0x04, 0x00,						//Header, Klasse, Länge
										0x00, 0x00, 0x08, 0x00,
										0x16, 0x74};										//Checksummen

//32.16.13 UBX-MON-VER (0x0A 0x04)
static const uint8_t getUBXVersion[] = {0xB5, 0x62, 0x0A, 0x04, 0x00, 0x00, 0xE, 0x34}; 	//Header, Klasse, Länge, Checksummen


static const uint8_t setPowerModeSettings[] = {0xB5, 0x62, 0x06, 0x86, 0x08, 0x00, 			//Header, Klasse und Länge
												0x00,										//Version
												0x02,										//Power Setup Value -> Intervall-Modus
												0x00, 0x00,									//Update- und Suchperisodenzeit = 0s
												0x01, 0x00,									//onTime -Zeit = 1s
												0x00, 0x00,									//Reserviert
												0x97, 0x6C									//Checksummen
												};

//32.10.30 UBX-CFG-RXM (0x06 0x11)
static const uint8_t setPowerMode[] = {0xB5, 0x62, 0x06, 0x11, 0x02, 0x00,					//Header, Klasse und Länge
											0x00, 											//Reserviert
											0x01,											//Power Save Modus
											0x1A, 0x82};									//Checksummen

//32.10.23 UBX-CFG-PM2 (0x06 0x3B)
static const uint8_t setPowerModeSettingsPM2[] = {0xB5, 0x62, 0x06, 0x3B, 0x30, 0x00,		//Header, Klasse und Länge
														0x02,								//Message-Version
														0x00,								//Reserviert
														0x00,								//maxStartupStateDur = 60s -> maximal in Erfassung
														0x00, 								//Reserviert
														0x00, 0x00, 0x01, 0x00,				//Flaggen, DoNotEnterOff = 1,
														0x14, 0x00, 0x00, 0x00,				//updatePeriod = 10 -> warte auf externes Signal für erneuten Fix
														0x00, 0x00, 0x00, 0x00,				//searchPeriod = 10 -> Den Sartup nicht erneut versuchen
														0x00, 0x00, 0x00, 0x00,				//gridOffset = 0, nicht verwendet
														0x05, 0x00,							//onTime =30s Sekunden lang Tracking
														0x1E, 0x00,//0x2C, 0x01,							//minAcqTime = 300s -> Standard für minAcqTime laut PMS
														0x00, 0x00, 0x00, 0x00,	0x00, 0x00, 0x00, 0x00,	0x00, 0x00, 0x00, 0x00,	0x00, 0x00, 0x00, 0x00,	0x00, 0x00, 0x00, 0x00,	// Reserviert
														0x00, 0x00, 0x00, 0x00,				//extintInactivityMs = 0, deaktiviert
														0xAB, 0x9B};						//Checksummen

//32.10.29 UBX-CFG-RST (0x06 0x04)
static const uint8_t UBXColdStartReset[] = {0xB5, 0x62, 0x06, 0x04, 0x04, 0x00,						//Header, Klasse, Länge
												0xFF, 0xFF,											//0xFFFF = Cold Start
												0x00, 												//direkter Hardwarereset
												0x00,												//Reserviert
												0x0C, 0x5D};										//Checksummen

//32.18.3 UBX-RXM-PMREQ (0x02 0x41)
static const uint8_t setPMDuration[] = {0xB5, 0x62, 0x02, 0x41, 0x10, 0x00,							//Header, Klasse, Länge
												0x00, 												//Nachrichtenversion
												0x00, 0x00, 0x00,									//Reserviert
												0x00, 0x00, 0x00, 0x00,								//Dauer der Aktivität, 0 = undendlich
												0x00, 0x00, 0x00, 0x00,								//Flaggen,
												0x08, 0x00, 0x00, 0x00,								//Aufwachwege, 8 = UART
												0x5B, 0x3B};										//Checksummen
//32.10.24 UBX-CFG-PMS (0x06 0x86)
static const uint8_t setPMS[] = {0xB5, 0x62, 0x06, 0x86, 0x08, 0x00,
										0x00,														//Version
										0x00,														//Full Power Modus
										0x00, 0x00,													//Such- und Updateperioden, 0 bei Full Power
										0x00, 0x00,													//OnTime, 0 bei Full Power
										0x00, 0x00,													//Reserviert
										0x94, 0x5A};												//Checksummen

void GNSS_Init(GNSS_StateHandle *GNSS, UART_HandleTypeDef *huart);
void GNSS_LoadConfig(GNSS_StateHandle *GNSS);
void GNSS_ParseBuffer(GNSS_StateHandle *GNSS);


void GNSS_GetPVTData(GNSS_StateHandle *GNSS);
void GNSS_GetPVTData_DMA(GNSS_StateHandle *GNSS);
void GNSS_ParsePVTData(GNSS_StateHandle *GNSS, int varStart);



#endif /* SAM_M8Q_SAM_M8Q_H_ */
