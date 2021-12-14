const Datenformat = (BToken, HWSName, BGrad, LGrad) => {
    const MessageBody = {

        validate_only: false,
        message: {
            data: {
                HName: HWSName,
                BrGrad: BGrad,
                LaeGrad: LGrad
            },
            token: BToken
        }
      };

    return MessageBody;
};

module.exports.Datenformat = Datenformat;