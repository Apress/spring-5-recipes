package court

class DailyNoticeTagLib {
    static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    static namespace = 'court'
    def promoDailyAd = { attrs, body ->
        def dayoftheweek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        out << body() << (dayoftheweek == 7 ?
                "We have special reservation offers for Sunday!": "No special offers")
    }

    def upcomingPromos  = { attrs, body ->
        def dayoftheweek = attrs['offerdate']
        out << body() << (dayoftheweek == 7 ?
                "We have special reservation offers for Saturday!": "No special offers")
    }
}
