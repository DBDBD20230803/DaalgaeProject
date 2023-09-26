// 날씨 좌표 배열

const weatherCalcXY = [
    {
        "level1": "서울특별시",
        "level2": "종로구",
        "x": 60,
        "y": 127
    },
    {
        "level1": "서울특별시",
        "level2": "중구",
        "x": 60,
        "y": 127
    },
    {
        "level1": "서울특별시",
        "level2": "용산구",
        "x": 60,
        "y": 126
    },
    {
        "level1": "서울특별시",
        "level2": "성동구",
        "x": 61,
        "y": 127
    },
    {
        "level1": "서울특별시",
        "level2": "광진구",
        "x": 62,
        "y": 126
    },
    {
        "level1": "서울특별시",
        "level2": "동대문구",
        "x": 61,
        "y": 127
    },
    {
        "level1": "서울특별시",
        "level2": "중랑구",
        "x": 62,
        "y": 128
    },
    {
        "level1": "서울특별시",
        "level2": "성북구",
        "x": 61,
        "y": 127
    },
    {
        "level1": "서울특별시",
        "level2": "강북구",
        "x": 61,
        "y": 128
    },
    {
        "level1": "서울특별시",
        "level2": "도봉구",
        "x": 61,
        "y": 129
    },
    {
        "level1": "서울특별시",
        "level2": "노원구",
        "x": 61,
        "y": 129
    },
    {
        "level1": "서울특별시",
        "level2": "은평구",
        "x": 59,
        "y": 127
    },
    {
        "level1": "서울특별시",
        "level2": "서대문구",
        "x": 59,
        "y": 127
    },
    {
        "level1": "서울특별시",
        "level2": "마포구",
        "x": 59,
        "y": 127
    },
    {
        "level1": "서울특별시",
        "level2": "양천구",
        "x": 58,
        "y": 126
    },
    {
        "level1": "서울특별시",
        "level2": "강서구",
        "x": 58,
        "y": 126
    },
    {
        "level1": "서울특별시",
        "level2": "구로구",
        "x": 58,
        "y": 125
    },
    {
        "level1": "서울특별시",
        "level2": "금천구",
        "x": 59,
        "y": 124
    },
    {
        "level1": "서울특별시",
        "level2": "영등포구",
        "x": 58,
        "y": 126
    },
    {
        "level1": "서울특별시",
        "level2": "동작구",
        "x": 59,
        "y": 125
    },
    {
        "level1": "서울특별시",
        "level2": "관악구",
        "x": 59,
        "y": 125
    },
    {
        "level1": "서울특별시",
        "level2": "서초구",
        "x": 61,
        "y": 125
    },
    {
        "level1": "서울특별시",
        "level2": "강남구",
        "x": 61,
        "y": 126
    },
    {
        "level1": "서울특별시",
        "level2": "송파구",
        "x": 62,
        "y": 126
    },
    {
        "level1": "서울특별시",
        "level2": "강동구",
        "x": 62,
        "y": 126
    },
    {
        "level1": "부산광역시",
        "level2": "중구",
        "x": 97,
        "y": 74
    },
    {
        "level1": "부산광역시",
        "level2": "서구",
        "x": 97,
        "y": 74
    },
    {
        "level1": "부산광역시",
        "level2": "동구",
        "x": 98,
        "y": 75
    },
    {
        "level1": "부산광역시",
        "level2": "영도구",
        "x": 98,
        "y": 74
    },
    {
        "level1": "부산광역시",
        "level2": "부산진구",
        "x": 97,
        "y": 75
    },
    {
        "level1": "부산광역시",
        "level2": "동래구",
        "x": 98,
        "y": 76
    },
    {
        "level1": "부산광역시",
        "level2": "남구",
        "x": 98,
        "y": 75
    },
    {
        "level1": "부산광역시",
        "level2": "북구",
        "x": 96,
        "y": 76
    },
    {
        "level1": "부산광역시",
        "level2": "해운대구",
        "x": 99,
        "y": 75
    },
    {
        "level1": "부산광역시",
        "level2": "사하구",
        "x": 96,
        "y": 74
    },
    {
        "level1": "부산광역시",
        "level2": "금정구",
        "x": 98,
        "y": 77
    },
    {
        "level1": "부산광역시",
        "level2": "강서구",
        "x": 96,
        "y": 76
    },
    {
        "level1": "부산광역시",
        "level2": "연제구",
        "x": 98,
        "y": 76
    },
    {
        "level1": "부산광역시",
        "level2": "수영구",
        "x": 99,
        "y": 75
    },
    {
        "level1": "부산광역시",
        "level2": "사상구",
        "x": 96,
        "y": 75
    },
    {
        "level1": "부산광역시",
        "level2": "기장군",
        "x": 100,
        "y": 77
    },
    {
        "level1": "대구광역시",
        "level2": "중구",
        "x": 89,
        "y": 90
    },
    {
        "level1": "대구광역시",
        "level2": "동구",
        "x": 90,
        "y": 91
    },
    {
        "level1": "대구광역시",
        "level2": "서구",
        "x": 88,
        "y": 90
    },
    {
        "level1": "대구광역시",
        "level2": "남구",
        "x": 89,
        "y": 90
    },
    {
        "level1": "대구광역시",
        "level2": "북구",
        "x": 89,
        "y": 91
    },
    {
        "level1": "대구광역시",
        "level2": "수성구",
        "x": 89,
        "y": 90
    },
    {
        "level1": "대구광역시",
        "level2": "달서구",
        "x": 88,
        "y": 90
    },
    {
        "level1": "대구광역시",
        "level2": "달성군",
        "x": 86,
        "y": 88
    },
    {
        "level1": "인천광역시",
        "level2": "중구",
        "x": 54,
        "y": 125
    },
    {
        "level1": "인천광역시",
        "level2": "동구",
        "x": 54,
        "y": 125
    },
    {
        "level1": "인천광역시",
        "level2": "미추홀구",
        "x": 54,
        "y": 124
    },
    {
        "level1": "인천광역시",
        "level2": "연수구",
        "x": 55,
        "y": 123
    },
    {
        "level1": "인천광역시",
        "level2": "남동구",
        "x": 56,
        "y": 124
    },
    {
        "level1": "인천광역시",
        "level2": "부평구",
        "x": 55,
        "y": 125
    },
    {
        "level1": "인천광역시",
        "level2": "계양구",
        "x": 56,
        "y": 126
    },
    {
        "level1": "인천광역시",
        "level2": "서구",
        "x": 55,
        "y": 126
    },
    {
        "level1": "인천광역시",
        "level2": "강화군",
        "x": 51,
        "y": 130
    },
    {
        "level1": "인천광역시",
        "level2": "옹진군",
        "x": 54,
        "y": 124
    },
    {
        "level1": "광주광역시",
        "level2": "동구",
        "x": 60,
        "y": 74
    },
    {
        "level1": "광주광역시",
        "level2": "서구",
        "x": 59,
        "y": 74
    },
    {
        "level1": "광주광역시",
        "level2": "남구",
        "x": 59,
        "y": 73
    },
    {
        "level1": "광주광역시",
        "level2": "북구",
        "x": 59,
        "y": 75
    },
    {
        "level1": "광주광역시",
        "level2": "광산구",
        "x": 57,
        "y": 74
    },
    {
        "level1": "대전광역시",
        "level2": "동구",
        "x": 68,
        "y": 100
    },
    {
        "level1": "대전광역시",
        "level2": "중구",
        "x": 68,
        "y": 100
    },
    {
        "level1": "대전광역시",
        "level2": "서구",
        "x": 67,
        "y": 100
    },
    {
        "level1": "대전광역시",
        "level2": "유성구",
        "x": 67,
        "y": 101
    },
    {
        "level1": "대전광역시",
        "level2": "대덕구",
        "x": 68,
        "y": 100
    },
    {
        "level1": "울산광역시",
        "level2": "중구",
        "x": 102,
        "y": 84
    },
    {
        "level1": "울산광역시",
        "level2": "남구",
        "x": 102,
        "y": 84
    },
    {
        "level1": "울산광역시",
        "level2": "동구",
        "x": 104,
        "y": 83
    },
    {
        "level1": "울산광역시",
        "level2": "북구",
        "x": 103,
        "y": 85
    },
    {
        "level1": "울산광역시",
        "level2": "울주군",
        "x": 101,
        "y": 84
    },
    {
        "level1": "세종특별자치시",
        "level2": "세종특별자치시",
        "x": 66,
        "y": 103
    },
    {
        "level1": "경기도",
        "level2": "수원시장안구",
        "x": 60,
        "y": 121
    },
    {
        "level1": "경기도",
        "level2": "수원시권선구",
        "x": 60,
        "y": 120
    },
    {
        "level1": "경기도",
        "level2": "수원시팔달구",
        "x": 61,
        "y": 121
    },
    {
        "level1": "경기도",
        "level2": "수원시영통구",
        "x": 61,
        "y": 120
    },
    {
        "level1": "경기도",
        "level2": "성남시수정구",
        "x": 63,
        "y": 124
    },
    {
        "level1": "경기도",
        "level2": "성남시중원구",
        "x": 63,
        "y": 124
    },
    {
        "level1": "경기도",
        "level2": "성남시분당구",
        "x": 62,
        "y": 123
    },
    {
        "level1": "경기도",
        "level2": "의정부시",
        "x": 61,
        "y": 130
    },
    {
        "level1": "경기도",
        "level2": "안양시만안구",
        "x": 59,
        "y": 123
    },
    {
        "level1": "경기도",
        "level2": "안양시동안구",
        "x": 59,
        "y": 123
    },
    {
        "level1": "경기도",
        "level2": "부천시",
        "x": 56,
        "y": 125
    },
    {
        "level1": "경기도",
        "level2": "광명시",
        "x": 58,
        "y": 125
    },
    {
        "level1": "경기도",
        "level2": "평택시",
        "x": 62,
        "y": 114
    },
    {
        "level1": "경기도",
        "level2": "동두천시",
        "x": 61,
        "y": 134
    },
    {
        "level1": "경기도",
        "level2": "안산시상록구",
        "x": 58,
        "y": 121
    },
    {
        "level1": "경기도",
        "level2": "안산시단원구",
        "x": 57,
        "y": 121
    },
    {
        "level1": "경기도",
        "level2": "고양시덕양구",
        "x": 57,
        "y": 128
    },
    {
        "level1": "경기도",
        "level2": "고양시일산동구",
        "x": 56,
        "y": 129
    },
    {
        "level1": "경기도",
        "level2": "고양시일산서구",
        "x": 56,
        "y": 129
    },
    {
        "level1": "경기도",
        "level2": "과천시",
        "x": 60,
        "y": 124
    },
    {
        "level1": "경기도",
        "level2": "구리시",
        "x": 62,
        "y": 127
    },
    {
        "level1": "경기도",
        "level2": "남양주시",
        "x": 64,
        "y": 128
    },
    {
        "level1": "경기도",
        "level2": "오산시",
        "x": 62,
        "y": 118
    },
    {
        "level1": "경기도",
        "level2": "시흥시",
        "x": 57,
        "y": 123
    },
    {
        "level1": "경기도",
        "level2": "군포시",
        "x": 59,
        "y": 122
    },
    {
        "level1": "경기도",
        "level2": "의왕시",
        "x": 60,
        "y": 122
    },
    {
        "level1": "경기도",
        "level2": "하남시",
        "x": 64,
        "y": 126
    },
    {
        "level1": "경기도",
        "level2": "용인시처인구",
        "x": 64,
        "y": 119
    },
    {
        "level1": "경기도",
        "level2": "용인시기흥구",
        "x": 62,
        "y": 120
    },
    {
        "level1": "경기도",
        "level2": "용인시수지구",
        "x": 62,
        "y": 121
    },
    {
        "level1": "경기도",
        "level2": "파주시",
        "x": 56,
        "y": 131
    },
    {
        "level1": "경기도",
        "level2": "이천시",
        "x": 68,
        "y": 121
    },
    {
        "level1": "경기도",
        "level2": "안성시",
        "x": 65,
        "y": 115
    },
    {
        "level1": "경기도",
        "level2": "김포시",
        "x": 55,
        "y": 128
    },
    {
        "level1": "경기도",
        "level2": "화성시",
        "x": 57,
        "y": 119
    },
    {
        "level1": "경기도",
        "level2": "광주시",
        "x": 65,
        "y": 123
    },
    {
        "level1": "경기도",
        "level2": "양주시",
        "x": 61,
        "y": 131
    },
    {
        "level1": "경기도",
        "level2": "포천시",
        "x": 64,
        "y": 134
    },
    {
        "level1": "경기도",
        "level2": "여주시",
        "x": 71,
        "y": 121
    },
    {
        "level1": "경기도",
        "level2": "연천군",
        "x": 61,
        "y": 138
    },
    {
        "level1": "경기도",
        "level2": "가평군",
        "x": 69,
        "y": 133
    },
    {
        "level1": "경기도",
        "level2": "양평군",
        "x": 69,
        "y": 125
    },
    {
        "level1": "충청북도",
        "level2": "청주시상당구",
        "x": 69,
        "y": 106
    },
    {
        "level1": "충청북도",
        "level2": "청주시서원구",
        "x": 69,
        "y": 107
    },
    {
        "level1": "충청북도",
        "level2": "청주시흥덕구",
        "x": 67,
        "y": 106
    },
    {
        "level1": "충청북도",
        "level2": "청주시청원구",
        "x": 69,
        "y": 107
    },
    {
        "level1": "충청북도",
        "level2": "충주시",
        "x": 76,
        "y": 114
    },
    {
        "level1": "충청북도",
        "level2": "제천시",
        "x": 81,
        "y": 118
    },
    {
        "level1": "충청북도",
        "level2": "보은군",
        "x": 73,
        "y": 103
    },
    {
        "level1": "충청북도",
        "level2": "옥천군",
        "x": 71,
        "y": 99
    },
    {
        "level1": "충청북도",
        "level2": "영동군",
        "x": 74,
        "y": 97
    },
    {
        "level1": "충청북도",
        "level2": "증평군",
        "x": 71,
        "y": 110
    },
    {
        "level1": "충청북도",
        "level2": "진천군",
        "x": 68,
        "y": 111
    },
    {
        "level1": "충청북도",
        "level2": "괴산군",
        "x": 74,
        "y": 111
    },
    {
        "level1": "충청북도",
        "level2": "음성군",
        "x": 72,
        "y": 113
    },
    {
        "level1": "충청북도",
        "level2": "단양군",
        "x": 84,
        "y": 115
    },
    {
        "level1": "충청남도",
        "level2": "천안시동남구",
        "x": 63,
        "y": 110
    },
    {
        "level1": "충청남도",
        "level2": "천안시서북구",
        "x": 63,
        "y": 112
    },
    {
        "level1": "충청남도",
        "level2": "공주시",
        "x": 63,
        "y": 102
    },
    {
        "level1": "충청남도",
        "level2": "보령시",
        "x": 54,
        "y": 100
    },
    {
        "level1": "충청남도",
        "level2": "아산시",
        "x": 60,
        "y": 110
    },
    {
        "level1": "충청남도",
        "level2": "서산시",
        "x": 51,
        "y": 110
    },
    {
        "level1": "충청남도",
        "level2": "논산시",
        "x": 62,
        "y": 97
    },
    {
        "level1": "충청남도",
        "level2": "계룡시",
        "x": 65,
        "y": 99
    },
    {
        "level1": "충청남도",
        "level2": "당진시",
        "x": 54,
        "y": 112
    },
    {
        "level1": "충청남도",
        "level2": "금산군",
        "x": 69,
        "y": 95
    },
    {
        "level1": "충청남도",
        "level2": "부여군",
        "x": 59,
        "y": 99
    },
    {
        "level1": "충청남도",
        "level2": "서천군",
        "x": 55,
        "y": 94
    },
    {
        "level1": "충청남도",
        "level2": "청양군",
        "x": 57,
        "y": 103
    },
    {
        "level1": "충청남도",
        "level2": "홍성군",
        "x": 55,
        "y": 106
    },
    {
        "level1": "충청남도",
        "level2": "예산군",
        "x": 58,
        "y": 107
    },
    {
        "level1": "충청남도",
        "level2": "태안군",
        "x": 48,
        "y": 109
    },
    {
        "level1": "전라북도",
        "level2": "전주시완산구",
        "x": 63,
        "y": 89
    },
    {
        "level1": "전라북도",
        "level2": "전주시덕진구",
        "x": 63,
        "y": 89
    },
    {
        "level1": "전라북도",
        "level2": "군산시",
        "x": 56,
        "y": 92
    },
    {
        "level1": "전라북도",
        "level2": "익산시",
        "x": 60,
        "y": 91
    },
    {
        "level1": "전라북도",
        "level2": "정읍시",
        "x": 58,
        "y": 83
    },
    {
        "level1": "전라북도",
        "level2": "남원시",
        "x": 68,
        "y": 80
    },
    {
        "level1": "전라북도",
        "level2": "김제시",
        "x": 59,
        "y": 88
    },
    {
        "level1": "전라북도",
        "level2": "완주군",
        "x": 63,
        "y": 89
    },
    {
        "level1": "전라북도",
        "level2": "진안군",
        "x": 68,
        "y": 88
    },
    {
        "level1": "전라북도",
        "level2": "무주군",
        "x": 72,
        "y": 93
    },
    {
        "level1": "전라북도",
        "level2": "장수군",
        "x": 70,
        "y": 85
    },
    {
        "level1": "전라북도",
        "level2": "임실군",
        "x": 66,
        "y": 84
    },
    {
        "level1": "전라북도",
        "level2": "순창군",
        "x": 63,
        "y": 79
    },
    {
        "level1": "전라북도",
        "level2": "고창군",
        "x": 56,
        "y": 80
    },
    {
        "level1": "전라북도",
        "level2": "부안군",
        "x": 56,
        "y": 87
    },
    {
        "level1": "전라남도",
        "level2": "목포시",
        "x": 50,
        "y": 67
    },
    {
        "level1": "전라남도",
        "level2": "여수시",
        "x": 73,
        "y": 66
    },
    {
        "level1": "전라남도",
        "level2": "순천시",
        "x": 70,
        "y": 70
    },
    {
        "level1": "전라남도",
        "level2": "나주시",
        "x": 56,
        "y": 71
    },
    {
        "level1": "전라남도",
        "level2": "광양시",
        "x": 73,
        "y": 70
    },
    {
        "level1": "전라남도",
        "level2": "담양군",
        "x": 61,
        "y": 78
    },
    {
        "level1": "전라남도",
        "level2": "곡성군",
        "x": 66,
        "y": 77
    },
    {
        "level1": "전라남도",
        "level2": "구례군",
        "x": 69,
        "y": 75
    },
    {
        "level1": "전라남도",
        "level2": "고흥군",
        "x": 66,
        "y": 62
    },
    {
        "level1": "전라남도",
        "level2": "보성군",
        "x": 62,
        "y": 66
    },
    {
        "level1": "전라남도",
        "level2": "화순군",
        "x": 61,
        "y": 72
    },
    {
        "level1": "전라남도",
        "level2": "장흥군",
        "x": 59,
        "y": 64
    },
    {
        "level1": "전라남도",
        "level2": "강진군",
        "x": 57,
        "y": 63
    },
    {
        "level1": "전라남도",
        "level2": "해남군",
        "x": 54,
        "y": 61
    },
    {
        "level1": "전라남도",
        "level2": "영암군",
        "x": 56,
        "y": 66
    },
    {
        "level1": "전라남도",
        "level2": "무안군",
        "x": 52,
        "y": 71
    },
    {
        "level1": "전라남도",
        "level2": "함평군",
        "x": 52,
        "y": 72
    },
    {
        "level1": "전라남도",
        "level2": "영광군",
        "x": 52,
        "y": 77
    },
    {
        "level1": "전라남도",
        "level2": "장성군",
        "x": 57,
        "y": 77
    },
    {
        "level1": "전라남도",
        "level2": "완도군",
        "x": 57,
        "y": 56
    },
    {
        "level1": "전라남도",
        "level2": "진도군",
        "x": 48,
        "y": 59
    },
    {
        "level1": "전라남도",
        "level2": "신안군",
        "x": 50,
        "y": 66
    },
    {
        "level1": "경상북도",
        "level2": "포항시남구",
        "x": 102,
        "y": 94
    },
    {
        "level1": "경상북도",
        "level2": "포항시북구",
        "x": 102,
        "y": 95
    },
    {
        "level1": "경상북도",
        "level2": "경주시",
        "x": 100,
        "y": 91
    },
    {
        "level1": "경상북도",
        "level2": "김천시",
        "x": 80,
        "y": 96
    },
    {
        "level1": "경상북도",
        "level2": "안동시",
        "x": 91,
        "y": 106
    },
    {
        "level1": "경상북도",
        "level2": "구미시",
        "x": 84,
        "y": 96
    },
    {
        "level1": "경상북도",
        "level2": "영주시",
        "x": 89,
        "y": 111
    },
    {
        "level1": "경상북도",
        "level2": "영천시",
        "x": 95,
        "y": 93
    },
    {
        "level1": "경상북도",
        "level2": "상주시",
        "x": 81,
        "y": 102
    },
    {
        "level1": "경상북도",
        "level2": "문경시",
        "x": 81,
        "y": 106
    },
    {
        "level1": "경상북도",
        "level2": "경산시",
        "x": 91,
        "y": 90
    },
    {
        "level1": "경상북도",
        "level2": "군위군",
        "x": 88,
        "y": 99
    },
    {
        "level1": "경상북도",
        "level2": "의성군",
        "x": 90,
        "y": 101
    },
    {
        "level1": "경상북도",
        "level2": "청송군",
        "x": 96,
        "y": 103
    },
    {
        "level1": "경상북도",
        "level2": "영양군",
        "x": 97,
        "y": 108
    },
    {
        "level1": "경상북도",
        "level2": "영덕군",
        "x": 102,
        "y": 103
    },
    {
        "level1": "경상북도",
        "level2": "청도군",
        "x": 91,
        "y": 86
    },
    {
        "level1": "경상북도",
        "level2": "고령군",
        "x": 83,
        "y": 87
    },
    {
        "level1": "경상북도",
        "level2": "성주군",
        "x": 83,
        "y": 91
    },
    {
        "level1": "경상북도",
        "level2": "칠곡군",
        "x": 85,
        "y": 93
    },
    {
        "level1": "경상북도",
        "level2": "예천군",
        "x": 86,
        "y": 107
    },
    {
        "level1": "경상북도",
        "level2": "봉화군",
        "x": 90,
        "y": 113
    },
    {
        "level1": "경상북도",
        "level2": "울진군",
        "x": 102,
        "y": 115
    },
    {
        "level1": "경상북도",
        "level2": "울릉군",
        "x": 127,
        "y": 127
    },
    {
        "level1": "경상남도",
        "level2": "창원시의창구",
        "x": 90,
        "y": 77
    },
    {
        "level1": "경상남도",
        "level2": "창원시성산구",
        "x": 91,
        "y": 76
    },
    {
        "level1": "경상남도",
        "level2": "창원시마산합포구",
        "x": 89,
        "y": 76
    },
    {
        "level1": "경상남도",
        "level2": "창원시마산회원구",
        "x": 89,
        "y": 76
    },
    {
        "level1": "경상남도",
        "level2": "창원시진해구",
        "x": 91,
        "y": 75
    },
    {
        "level1": "경상남도",
        "level2": "진주시",
        "x": 81,
        "y": 75
    },
    {
        "level1": "경상남도",
        "level2": "통영시",
        "x": 87,
        "y": 68
    },
    {
        "level1": "경상남도",
        "level2": "사천시",
        "x": 80,
        "y": 71
    },
    {
        "level1": "경상남도",
        "level2": "김해시",
        "x": 95,
        "y": 77
    },
    {
        "level1": "경상남도",
        "level2": "밀양시",
        "x": 92,
        "y": 83
    },
    {
        "level1": "경상남도",
        "level2": "거제시",
        "x": 90,
        "y": 69
    },
    {
        "level1": "경상남도",
        "level2": "양산시",
        "x": 97,
        "y": 79
    },
    {
        "level1": "경상남도",
        "level2": "의령군",
        "x": 83,
        "y": 78
    },
    {
        "level1": "경상남도",
        "level2": "함안군",
        "x": 86,
        "y": 77
    },
    {
        "level1": "경상남도",
        "level2": "창녕군",
        "x": 87,
        "y": 83
    },
    {
        "level1": "경상남도",
        "level2": "고성군",
        "x": 85,
        "y": 71
    },
    {
        "level1": "경상남도",
        "level2": "남해군",
        "x": 77,
        "y": 68
    },
    {
        "level1": "경상남도",
        "level2": "하동군",
        "x": 74,
        "y": 73
    },
    {
        "level1": "경상남도",
        "level2": "산청군",
        "x": 76,
        "y": 80
    },
    {
        "level1": "경상남도",
        "level2": "함양군",
        "x": 74,
        "y": 82
    },
    {
        "level1": "경상남도",
        "level2": "거창군",
        "x": 77,
        "y": 86
    },
    {
        "level1": "경상남도",
        "level2": "합천군",
        "x": 81,
        "y": 84
    },
    {
        "level1": "제주특별자치도",
        "level2": "제주시",
        "x": 53,
        "y": 38
    },
    {
        "level1": "제주특별자치도",
        "level2": "서귀포시",
        "x": 52,
        "y": 33
    },
    {
        "level1": "강원특별자치도",
        "level2": "춘천시",
        "x": 73,
        "y": 134
    },
    {
        "level1": "강원특별자치도",
        "level2": "원주시",
        "x": 76,
        "y": 122
    },
    {
        "level1": "강원특별자치도",
        "level2": "강릉시",
        "x": 92,
        "y": 131
    },
    {
        "level1": "강원특별자치도",
        "level2": "동해시",
        "x": 97,
        "y": 127
    },
    {
        "level1": "강원특별자치도",
        "level2": "태백시",
        "x": 95,
        "y": 119
    },
    {
        "level1": "강원특별자치도",
        "level2": "속초시",
        "x": 87,
        "y": 141
    },
    {
        "level1": "강원특별자치도",
        "level2": "삼척시",
        "x": 98,
        "y": 125
    },
    {
        "level1": "강원특별자치도",
        "level2": "홍천군",
        "x": 75,
        "y": 130
    },
    {
        "level1": "강원특별자치도",
        "level2": "횡성군",
        "x": 77,
        "y": 125
    },
    {
        "level1": "강원특별자치도",
        "level2": "영월군",
        "x": 86,
        "y": 119
    },
    {
        "level1": "강원특별자치도",
        "level2": "평창군",
        "x": 84,
        "y": 123
    },
    {
        "level1": "강원특별자치도",
        "level2": "정선군",
        "x": 89,
        "y": 123
    },
    {
        "level1": "강원특별자치도",
        "level2": "철원군",
        "x": 65,
        "y": 139
    },
    {
        "level1": "강원특별자치도",
        "level2": "화천군",
        "x": 72,
        "y": 139
    },
    {
        "level1": "강원특별자치도",
        "level2": "양구군",
        "x": 77,
        "y": 139
    },
    {
        "level1": "강원특별자치도",
        "level2": "인제군",
        "x": 80,
        "y": 138
    },
    {
        "level1": "강원특별자치도",
        "level2": "고성군",
        "x": 85,
        "y": 145
    },
    {
        "level1": "강원특별자치도",
        "level2": "양양군",
        "x": 88,
        "y": 138
    }
];

// if(localStorage.getItem('email') != 'test@user.com') {
//     localStorage.setItem('email', 'test@user.com');
//     console.log(111);
// }
//
// console.log(localStorage.getItem('email'));

// 코드 부분

let address;
let today = new Date();

let year = today.getFullYear(); // 년도
let month = today.getMonth() + 1;  // 월
let date = today.getDate();  // 날짜
if(month < 10) {
    month = "0" + month;
}
let date1 = year + "" + month + date;
let date2 = year + "-" + month + "-" + date;

let hours = today.getHours();
let hourString;
if(hours < 9) {
    hourString = "0" + (hours + 1) + "00";
} else {
    hourString = (hours + 1) + "00";
}

let minutes = today.getMinutes();

let DanHours;
let choDanHours;

if(hours >= 23 && minutes > 10) {
    DanHours = "2300";
} else if(hours >= 20) {
    if(minutes > 10) {
        DanHours = "2000";
    } else {
        DanHours = "1700";
    }
} else if(hours >= 17) {
    if(minutes > 10) {
        DanHours = "1700";
    } else {
        DanHours = "1400";
    }
} else if(hours >= 14) {
    if(minutes > 10) {
        DanHours = "1400";
    } else {
        DanHours = "1100";
    }
} else if(hours >= 11) {
    if(minutes > 10) {
        DanHours = "1100";
    } else {
        DanHours = "0800";
    }
} else if(hours >= 8) {
    if(minutes > 10) {
        DanHours = "0800";
    } else {
        DanHours = "0500";
    }
} else if(hours >= 5) {
    if(minutes > 10) {
        DanHours = "0500";
    } else {
        DanHours = "0200";
    }
}else if(hours >= 2) {
    if(minutes > 10) {
        DanHours = "0200";
    }
}

if(minutes > 40) {
    if(hours < 10) {
        choDanHours = "0" + hours + "00";
    } else {
        choDanHours = hours + "00";
    }

} else {
    if(hours < 11) {
        choDanHours = "0" + (hours - 1) + "00";
    } else {
        choDanHours = (hours - 1) + "00";
    }
}

// console.log(121111);
// console.log(DanHours);
// console.log(choDanHours);
// console.log(121111);
// console.log(localStorage.getItem('savedDanHours'));
// console.log(localStorage.getItem('savedChoDanHours'));
// console.log(minutes);
// console.log(hourString);



if(localStorage.getItem('savedChoDanHours') == choDanHours && localStorage.getItem('savedDanHours') == DanHours) {
    $(window).on("load", function() {
        let weatherLevel = localStorage.getItem('savedWeatherLevel');
        let fineDustLevel = localStorage.getItem('savedFineDustLevel');
        let weatherNo = localStorage.getItem('savedWeatherNo');
        let temp = localStorage.getItem('temp');

        // console.log(weatherLevel);
        // console.log(fineDustLevel);
        // console.log(weatherNo);
        // console.log(temp);
        $('.weatherTemp0').append("온도 " + temp + "℃ | ");
        $('.weatherTemp1').append("온도 " + temp + "℃ | ");
        $('.weatherTemp2').append("온도 " + temp + "℃ | ");
        // 조건에 따른 이미지와 글씨
        switch (weatherLevel) {
            case "1":
                $('.weatherGood0').append('좋음 😄');
                $('.weatherGood1').append('좋음 😄');
                $('.weatherGood2').append('좋음 😄');
                break;
            case "2":
                // console.log(11122);
                $('.weatherGood0').append('보통 😐');
                $('.weatherGood1').append('보통 😐');
                $('.weatherGood2').append('보통 😐');
                break;
            case "3":
                $('.weatherGood0').append('나쁨 😫');
                $('.weatherGood1').append('나쁨 😫');
                $('.weatherGood2').append('나쁨 😫');
                break;
        }
        switch (fineDustLevel) {
            case "1":
                $('.weatherTemp0').append("미세먼지 좋음");
                $('.weatherTemp1').append("미세먼지 좋음");
                $('.weatherTemp2').append("미세먼지 좋음");
                break;
            case "2":
                // console.log(11122);
                $('.weatherTemp0').append("미세먼지 보통");
                $('.weatherTemp1').append("미세먼지 보통");
                $('.weatherTemp2').append("미세먼지 보통");
                break;
            case "3":
                $('.weatherTemp0').append("미세먼지 나쁨");
                $('.weatherTemp1').append("미세먼지 나쁨");
                $('.weatherTemp2').append("미세먼지 나쁨");
                break;
        }
        switch (weatherNo) {
            case "1":
                $('.weatherImage0').prop('src', "/images/weatherHot.png");
                $('.weatherImage1').prop('src', "/images/weatherHot.png");
                $('.weatherImage2').prop('src', "/images/weatherHot.png");
                break;
            case "2":
                $('.weatherImage0').prop('src', "/images/weatherCold.png");
                $('.weatherImage1').prop('src', "/images/weatherCold.png");
                $('.weatherImage2').prop('src', "/images/weatherCold.png");
                break;
            case "3":
                $('.weatherImage0').prop('src', "/images/weatherMuchRain.png");
                $('.weatherImage1').prop('src', "/images/weatherMuchRain.png");
                $('.weatherImage2').prop('src', "/images/weatherMuchRain.png");
                break;
            case "4":
                $('.weatherImage0').prop('src', "/images/weatherUmbrella.png");
                $('.weatherImage1').prop('src', "/images/weatherUmbrella.png");
                $('.weatherImage2').prop('src', "/images/weatherUmbrella.png");
                break;
            case "5":
                $('.weatherImage0').prop('src', "/images/weatherCloudSnow.png");
                $('.weatherImage1').prop('src', "/images/weatherCloudSnow.png");
                $('.weatherImage2').prop('src', "/images/weatherCloudSnow.png");
                break;
            case "6":
                $('.weatherImage0').prop('src', "/images/weatherCloudRain.png");
                $('.weatherImage1').prop('src', "/images/weatherCloudRain.png");
                $('.weatherImage2').prop('src', "/images/weatherCloudRain.png");
                break;
            case "7":
                $('.weatherImage0').prop('src', "/images/weatherSnow.png");
                $('.weatherImage1').prop('src', "/images/weatherSnow.png");
                $('.weatherImage2').prop('src', "/images/weatherSnow.png");
                break;
            case "8":
                if(hours <=19) {
                    $('.weatherImage0').prop('src', "/images/weatherOnlyCloud.png");
                    $('.weatherImage1').prop('src', "/images/weatherOnlyCloud.png");
                    $('.weatherImage2').prop('src', "/images/weatherOnlyCloud.png");
                } else {
                    $('.weatherImage0').prop('src', "/images/weatherCloudMoon.png");
                    $('.weatherImage1').prop('src', "/images/weatherCloudMoon.png");
                    $('.weatherImage2').prop('src', "/images/weatherCloudMoon.png");
                    // $("#mainBackground").css({"background":"url(/images/backWeatherNight.jpg)"});
                    // $("#mainBackground").css({"width":"100%"});
                    // $("#mainBackground").css({"height":"1080px"});
                    // console.log('change');
                }
                break;
            case "9":
                $('.weatherImage0').prop('src', "/images/weatherTwoCloud.png");
                $('.weatherImage1').prop('src', "/images/weatherTwoCloud.png");
                $('.weatherImage2').prop('src', "/images/weatherTwoCloud.png");
                break;
            case "10":
                $('.weatherImage0').prop('src', "/images/windy.png");
                $('.weatherImage1').prop('src', "/images/windy.png");
                $('.weatherImage2').prop('src', "/images/windy.png");
                break;
            default:
                if(hours <=19) {
                    // console.log(11123);
                    $('.weatherImage0').prop('src', "/images/weatherCloudSun.png");
                    $('.weatherImage1').prop('src', "/images/weatherCloudSun.png");
                    $('.weatherImage2').prop('src', "/images/weatherCloudSun.png");
                } else {
                    $('.weatherImage0').prop('src', "/images/weatherCrescent.png");
                    $('.weatherImage1').prop('src', "/images/weatherCrescent.png");
                    $('.weatherImage2').prop('src', "/images/weatherCrescent.png");
                }
        }
    });
} else {
    // 주소와 날씨 좌표
    let addressLevel1;
    let addressLevel2;
    let weatherX;
    let weatherY;

// 날씨 이미지, 글 표시를 위한 변수
    let weatherNo = 0;
    let weatherLevel = 0;
    let fineDustLevel = 0;
    let tempCheck = 0;

// 좌표 알아내기
    $(function(){
        let startPos;
        let geoSuccess = function(position) {
            startPos = position;
            let latitudeValue = startPos.coords.latitude;
            let longitudeValue = startPos.coords.longitude;
            let userX = String(latitudeValue);
            let userY = String(longitudeValue);
            if(userX.length !==0 && userY.length !==0) {
                localStorage.setItem("userX", userX);
                localStorage.setItem("userY", userY);
            }
            // 좌표로 주소 찾기: 구까지는 정확한 것 같다.
            let makeUrlFindLocation = "https://api.vworld.kr/req/address?service=address&request=getAddress&version=2.0&crs=epsg:4326&point=";
            makeUrlFindLocation += longitudeValue;
            makeUrlFindLocation += ","
            makeUrlFindLocation += latitudeValue;
            makeUrlFindLocation += "&format=json&type=both&zipcode=true&simple=false&key=C04550B1-19FD-317B-8BC6-A71E49C061EF";

            $.ajax({
                type:"get",
                url:makeUrlFindLocation,
                dataType:"jsonp",
                success: function(data){
                    // console.log("통신성공1");
                    address = data.response.result[0].text.split(" ");
                    addressLevel1 = address[0];
                    addressLevel2 = address[1];
                    // 주소 레벨2로 날씨 전용 좌표 찾기
                    function isApple(element) {
                        if(element.level2 === addressLevel2)  {
                            return true;
                        }
                    }
                    const apple = weatherCalcXY.find(isApple);
                    weatherX = apple.x;
                    weatherY = apple.y;

                    // 날씨 전용 좌표로 날씨 정보 얻기

                    // 초단기실황
                    // https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?serviceKey=jwzbFiirkPF%2FrzTy3ceQqXb%2BuP8bvOFMVH3CizfiVQsy5EDFZCZekZa4S63aD460NnsTzQP4Ua5sfb5yT9stZg%3D%3D&pageNo=1&numOfRows=20&dataType=JSON&base_date=
                    // 20230917
                    // &base_time=1000&nx=
                    // 62&ny=126

                    // 단기 예보
                    // https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?serviceKey=jwzbFiirkPF%2FrzTy3ceQqXb%2BuP8bvOFMVH3CizfiVQsy5EDFZCZekZa4S63aD460NnsTzQP4Ua5sfb5yT9stZg%3D%3D&pageNo=1&numOfRows=300&dataType=JSON&base_date=

                    // 초단기 날씨 정보 알아내기
                    let makeUrlFindWeather1 = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?serviceKey=jwzbFiirkPF%2FrzTy3ceQqXb%2BuP8bvOFMVH3CizfiVQsy5EDFZCZekZa4S63aD460NnsTzQP4Ua5sfb5yT9stZg%3D%3D&pageNo=1&numOfRows=20&dataType=JSON&base_date=";
                    makeUrlFindWeather1 += date1;
                    makeUrlFindWeather1 += "&base_time=";
                    makeUrlFindWeather1 += choDanHours;
                    makeUrlFindWeather1 += "&nx=";
                    makeUrlFindWeather1 += weatherX;
                    makeUrlFindWeather1 += "&ny=";
                    makeUrlFindWeather1 += weatherY;
                    $.ajax({
                        type:"get",
                        url:makeUrlFindWeather1,
                        dataType:"json",
                        success: function(data){
                            // console.log(makeUrlFindWeather1);
                            // console.log(data);
                            // console.log("통신성공5");

                            // 초단기는 매시 40분 이후
                            // T1H 기온℃
                            // RN1 1시간 강수량 mm
                            // PTY 강수형태
                            // WSD 풍속

                            Outer: for(let a = 0; a < 8; a++) {
                                let itemSelect = data.response.body.items.item[a];
                                // console.log(date1);
                                if(itemSelect.category == 'T1H') {
                                    if(tempCheck == 0) {
                                        $('.weatherTemp0').append("온도 " + itemSelect.obsrValue + "℃ | ");
                                        $('.weatherTemp1').append("온도 " + itemSelect.obsrValue + "℃ | ");
                                        $('.weatherTemp2').append("온도 " + itemSelect.obsrValue + "℃ | ");
                                        localStorage.setItem('temp', itemSelect.obsrValue);
                                        tempCheck = 1;
                                    }
                                    if(itemSelect.obsrValue >= 28) {
                                        if(weatherLevel < 3) {
                                            weatherNo = 1;
                                            weatherLevel = 3;
                                        }
                                    }
                                    if(itemSelect.obsrValue <= 10) {
                                        if(weatherLevel < 3) {
                                            weatherNo = 2;
                                            weatherLevel = 3;
                                        }
                                    }
                                }

                                if(itemSelect.category == 'RN1') {
                                    if(itemSelect.obsrValue >= 20) {
                                        if(weatherLevel < 3) {
                                            weatherNo = 3;
                                            weatherLevel = 3;
                                        }
                                    }
                                }

                                if(itemSelect.category == 'PTY') {
                                    if(itemSelect.obsrValue == 1 || itemSelect.obsrValue == 2) {
                                        if(weatherLevel < 3) {
                                            weatherNo = 4;
                                            weatherLevel = 3;
                                        }
                                    }
                                    if(itemSelect.obsrValue == 3) {
                                        if(weatherLevel < 3) {
                                            weatherNo = 5;
                                            weatherLevel = 3;
                                        }
                                    }
                                    if(itemSelect.obsrValue == 4) {
                                        if(weatherLevel < 2) {
                                            weatherNo = 6;
                                            weatherLevel = 2;
                                        }
                                    }
                                }

                                if(itemSelect.category == 'WSD') {
                                    if(itemSelect.obsrValue >= 15) {
                                        if (weatherLevel < 3) {
                                            weatherNo = 10;
                                            weatherLevel = 3;
                                        }
                                    }
                                }

                                if(a == 8) {
                                    // console.log(weatherLevel);
                                    // console.log(weatherNo);
                                }
                            }

                            // 단기 날씨 정보 알아내기
                            let makeUrlFindWeather2 = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?serviceKey=jwzbFiirkPF%2FrzTy3ceQqXb%2BuP8bvOFMVH3CizfiVQsy5EDFZCZekZa4S63aD460NnsTzQP4Ua5sfb5yT9stZg%3D%3D&pageNo=1&numOfRows=50&dataType=JSON&base_date=";
                            makeUrlFindWeather2 += date1;
                            makeUrlFindWeather2 += "&base_time=";
                            makeUrlFindWeather2 += DanHours;
                            makeUrlFindWeather2 += "&nx=";
                            makeUrlFindWeather2 += weatherX;
                            makeUrlFindWeather2 += "&ny=";
                            makeUrlFindWeather2 += weatherY;

                            //
                            $.ajax({
                                type:"get",
                                url:makeUrlFindWeather2,
                                dataType:"json",
                                success: function(data){
                                    // console.log(makeUrlFindWeather2);
                                    // console.log(data);
                                    // console.log("통신성공2");

                                    // console.log(data);
                                    // let b = data.response.body.items;
                                    // alert(b.item[1]);
                                    // alert(data.response.body.items.item[0].fcstValue);

                                    // - Base_time : 0200, 0500, 0800, 1100, 1400, 1700, 2000, 2300 (1일 8회)
                                    // - API 제공 시간(~이후) : 02:10, 05:10, 08:10, 11:10, 14:10, 17:10, 20:10, 23:10

                                    // 단기

                                    // SNO	1시간 신적설	범주(1 cm)	8
                                    // 신적설(새로 쌓인 눈)이 5cm 이상

                                    // SKY	하늘상태	코드값	4
                                    // 하늘상태(SKY) 코드 : 맑음(1), 구름많음(3), 흐림(4)

                                    // ※단기에서 안 쓸 예정
                                    // POP	강수확률	%
                                    // 70% 이상 비올 확률 높음

                                    // PTY	강수형태	코드값	4
                                    // (단기) 없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4)

                                    // PCP	1시간 강수량	범주 (1 mm)	8
                                    // 20mm이상

                                    // TMP	1시간 기온	℃	10
                                    // 기온이 28도 이상이면 산책지수 나쁨

                                    // WSD	풍속	m/s	10
                                    // 풍속 15m/s

                                    Outer: for(let a = 0; a < 50; a++) {
                                        let itemSelect = data.response.body.items.item[a];
                                        // console.log(date1);
                                        if(itemSelect.fcstDate == date1 && itemSelect.fcstTime == hourString) {
                                            console.log(itemSelect.category);
                                            if(itemSelect.category == 'SNO') {
                                                if(itemSelect.fcstValue >= 5) {
                                                    if (weatherLevel < 3) {
                                                        weatherNo = 7;
                                                        weatherLevel = 3;
                                                    }
                                                }
                                            }
                                            if(itemSelect.category == 'SKY') {
                                                if(itemSelect.fcstValue == 3) {
                                                    if (weatherLevel < 2) {
                                                        weatherNo = 8;
                                                        weatherLevel = 1;
                                                    }
                                                }
                                                if(itemSelect.fcstValue == 4) {
                                                    if (weatherLevel < 2) {
                                                        weatherNo = 9;
                                                        weatherLevel = 1;
                                                    }
                                                }
                                            }
                                        }
                                        if(a == 50) {
                                            // console.log(weatherLevel);
                                            // console.log(weatherNo);
                                        }
                                    }

                                    // 주소 정보(주소 레벨1의 두글자로)로 미세 먼지 정보 찾기(좋음, 보통, 나쁨 이런 거)
                                    let makeurlFindDust1 = "https://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMinuDustFrcstDspth?serviceKey=jwzbFiirkPF%2FrzTy3ceQqXb%2BuP8bvOFMVH3CizfiVQsy5EDFZCZekZa4S63aD460NnsTzQP4Ua5sfb5yT9stZg%3D%3D&returnType=json&numOfRows=500&pageNo=1&searchDate=";
                                    makeurlFindDust1 += date2;
                                    makeurlFindDust1 += "&InformCode=PM10";
                                    $.ajax({
                                        type:"get",
                                        url:makeurlFindDust1,
                                        dataType:"json",
                                        success: function(data){
                                            // console.log("통신성공3");
                                            // 0
                                            let a = data.response.body.items[0].informGrade.split(",");
                                            for(let b of a) {
                                                if(b.substring(0, 2) === addressLevel1.substring(0, 2)) {
                                                    let c = b.indexOf(":");
                                                    if(b.substring(c+2) == '좋음') {
                                                        if(weatherLevel < 1) {
                                                            weatherLevel = 1;
                                                        }
                                                        if(fineDustLevel < 1) {
                                                            fineDustLevel = 1;
                                                        }
                                                    } else if(b.substring(c+2) == '보통') {
                                                        if(weatherLevel < 2) {
                                                            weatherLevel = 2;
                                                        }
                                                        if(fineDustLevel < 2) {
                                                            fineDustLevel = 2;
                                                        }
                                                    } else if(b.substring(c+2) == '나쁨') {
                                                        if(weatherLevel < 3) {
                                                            weatherLevel = 3;
                                                        }
                                                        if(fineDustLevel < 3) {
                                                            fineDustLevel = 3;
                                                        }
                                                    } else if(b.substring(c+2) == '매우') {
                                                        if(weatherLevel < 3) {
                                                            weatherLevel = 3;
                                                        }
                                                        if(fineDustLevel < 4) {
                                                            fineDustLevel = 4;
                                                        }
                                                    }
                                                    break;
                                                }
                                            }
                                            // 2
                                            let f = data.response.body.items[2].informGrade.split(",");
                                            for(let b of f) {
                                                if(b.substring(0, 2) === addressLevel1.substring(0, 2)) {
                                                    let c = b.indexOf(":");
                                                    if(b.substring(c+2) == '좋음') {
                                                        if(weatherLevel < 1) {
                                                            weatherLevel = 1;
                                                        }
                                                        if(fineDustLevel < 1) {
                                                            fineDustLevel = 1;
                                                        }
                                                    } else if(b.substring(c+2) == '보통') {
                                                        if(weatherLevel < 2) {
                                                            weatherLevel = 2;
                                                        }
                                                        if(fineDustLevel < 2) {
                                                            fineDustLevel = 2;
                                                        }
                                                    } else if(b.substring(c+2) == '나쁨') {
                                                        if(weatherLevel < 3) {
                                                            weatherLevel = 3;
                                                        }
                                                        if(fineDustLevel < 3) {
                                                            fineDustLevel = 3;
                                                        }
                                                    } else if(b.substring(c+2) == '매우') {
                                                        if(weatherLevel < 3) {
                                                            weatherLevel = 3;
                                                        }
                                                        if(fineDustLevel < 4) {
                                                            fineDustLevel = 4;
                                                        }
                                                    }
                                                    break;
                                                }
                                            }
                                            // 4
                                            /*let d = data.response.body.items[4].informGrade.split(",");
                                            for(let b of d) {
                                                if(b.substring(0, 2) === addressLevel1.substring(0, 2)) {
                                                    let c = b.indexOf(":");
                                                    if(b.substring(c+2) == '좋음') {
                                                        if(weatherLevel < 1) {
                                                            weatherLevel = 1;
                                                        }
                                                        if(fineDustLevel < 1) {
                                                            fineDustLevel = 1;
                                                        }
                                                    } else if(b.substring(c+2) == '보통') {
                                                        if(weatherLevel < 2) {
                                                            weatherLevel = 2;
                                                        }
                                                        if(fineDustLevel < 2) {
                                                            fineDustLevel = 2;
                                                        }
                                                    } else if(b.substring(c+2) == '나쁨') {
                                                        if(weatherLevel < 3) {
                                                            weatherLevel = 3;
                                                        }
                                                        if(fineDustLevel < 3) {
                                                            fineDustLevel = 3;
                                                        }
                                                    } else if(b.substring(c+2) == '매우') {
                                                        if(weatherLevel < 3) {
                                                            weatherLevel = 3;
                                                        }
                                                        if(fineDustLevel < 4) {
                                                            fineDustLevel = 4;
                                                        }
                                                    }
                                                    break;
                                                }
                                            }*/
                                            localStorage.setItem('savedWeatherLevel', weatherLevel);
                                            localStorage.setItem('savedFineDustLevel', fineDustLevel);
                                            localStorage.setItem('savedWeatherNo', weatherNo);

                                            // 조건에 따른 이미지와 글씨
                                            switch (weatherLevel) {
                                                case 1:
                                                    $('.weatherGood0').append('좋음 😄');
                                                    $('.weatherGood1').append('좋음 😄');
                                                    $('.weatherGood2').append('좋음 😄');
                                                    break;
                                                case 2:
                                                    $('.weatherGood0').append('보통 😐');
                                                    $('.weatherGood1').append('보통 😐');
                                                    $('.weatherGood2').append('보통 😐');
                                                    break;
                                                case 3:
                                                    $('.weatherGood0').append('나쁨 😫');
                                                    $('.weatherGood1').append('나쁨 😫');
                                                    $('.weatherGood2').append('나쁨 😫');
                                                    break;
                                            }
                                            switch (fineDustLevel) {
                                                case 1:
                                                    $('.weatherTemp0').append("미세먼지 좋음");
                                                    $('.weatherTemp1').append("미세먼지 좋음");
                                                    $('.weatherTemp2').append("미세먼지 좋음");
                                                    break;
                                                case 2:
                                                    $('.weatherTemp0').append("미세먼지 보통");
                                                    $('.weatherTemp1').append("미세먼지 보통");
                                                    $('.weatherTemp2').append("미세먼지 보통");
                                                    break;
                                                    $('.weatherTemp0').append("미세먼지 나쁨");
                                                    $('.weatherTemp1').append("미세먼지 나쁨");
                                                    $('.weatherTemp2').append("미세먼지 나쁨");
                                                case 3:
                                                    break;
                                            }
                                            switch (weatherNo) {
                                                case 1:
                                                    $('.weatherImage0').prop('src', "/images/weatherHot.png");
                                                    $('.weatherImage1').prop('src', "/images/weatherHot.png");
                                                    $('.weatherImage2').prop('src', "/images/weatherHot.png");
                                                    break;
                                                case 2:
                                                    $('.weatherImage0').prop('src', "/images/weatherCold.png");
                                                    $('.weatherImage1').prop('src', "/images/weatherCold.png");
                                                    $('.weatherImage2').prop('src', "/images/weatherCold.png");
                                                    break;
                                                case 3:
                                                    $('.weatherImage0').prop('src', "/images/weatherMuchRain.png");
                                                    $('.weatherImage1').prop('src', "/images/weatherMuchRain.png");
                                                    $('.weatherImage2').prop('src', "/images/weatherMuchRain.png");
                                                    break;
                                                case 4:
                                                    $('.weatherImage0').prop('src', "/images/weatherUmbrella.png");
                                                    $('.weatherImage1').prop('src', "/images/weatherUmbrella.png");
                                                    $('.weatherImage2').prop('src', "/images/weatherUmbrella.png");
                                                    break;
                                                case 5:
                                                    $('.weatherImage0').prop('src', "/images/weatherCloudSnow.png");
                                                    $('.weatherImage1').prop('src', "/images/weatherCloudSnow.png");
                                                    $('.weatherImage2').prop('src', "/images/weatherCloudSnow.png");
                                                    break;
                                                case 6:
                                                    $('.weatherImage0').prop('src', "/images/weatherCloudRain.png");
                                                    $('.weatherImage1').prop('src', "/images/weatherCloudRain.png");
                                                    $('.weatherImage2').prop('src', "/images/weatherCloudRain.png");
                                                    break;
                                                case 7:
                                                    $('.weatherImage0').prop('src', "/images/weatherSnow.png");
                                                    $('.weatherImage1').prop('src', "/images/weatherSnow.png");
                                                    $('.weatherImage2').prop('src', "/images/weatherSnow.png");
                                                    break;
                                                case 8:
                                                    if(hours <=19) {
                                                        $('.weatherImage0').prop('src', "/images/weatherOnlyCloud.png");
                                                        $('.weatherImage1').prop('src', "/images/weatherOnlyCloud.png");
                                                        $('.weatherImage2').prop('src', "/images/weatherOnlyCloud.png");
                                                    } else {
                                                        $('.weatherImage0').prop('src', "/images/weatherCloudMoon.png");
                                                        $('.weatherImage1').prop('src', "/images/weatherCloudMoon.png");
                                                        $('.weatherImage2').prop('src', "/images/weatherCloudMoon.png");
                                                        // $("#mainBackground").css({"background":"url(/images/backWeatherNight.jpg)"});
                                                        // $("#mainBackground").css({"width":"100%"});
                                                        // $("#mainBackground").css({"height":"1080px"});
                                                        // console.log('change');
                                                    }
                                                    break;
                                                case 9:
                                                    $('.weatherImage0').prop('src', "/images/weatherTwoCloud.png");
                                                    $('.weatherImage1').prop('src', "/images/weatherTwoCloud.png");
                                                    $('.weatherImage2').prop('src', "/images/weatherTwoCloud.png");
                                                    break;
                                                case 10:
                                                    $('.weatherImage0').prop('src', "/images/windy.png");
                                                    $('.weatherImage1').prop('src', "/images/windy.png");
                                                    $('.weatherImage2').prop('src', "/images/windy.png");
                                                    break;
                                                default:
                                                    if(hours <=19) {
                                                        $('.weatherImage0').prop('src', "/images/weatherCloudSun.png");
                                                        $('.weatherImage1').prop('src', "/images/weatherCloudSun.png");
                                                        $('.weatherImage2').prop('src', "/images/weatherCloudSun.png");
                                                    } else {
                                                        $('.weatherImage0').prop('src', "/images/weatherCrescent.png");
                                                        $('.weatherImage1').prop('src', "/images/weatherCrescent.png");
                                                        $('.weatherImage2').prop('src', "/images/weatherCrescent.png");
                                                        $('#mainBackground').prop('background-image', "url(\"/images/backWeatherNight.png\")")
                                                    }

                                            }
                                            localStorage.setItem('savedChoDanHours', choDanHours);
                                            localStorage.setItem('savedDanHours', DanHours);
                                        },
                                        error:function(){
                                            // console.log("통신에러3");
                                        }
                                    });
                                },
                                error:function(){
                                    // console.log("통신에러2");
                                }
                            });
                        },

                        error:function(){
                            // console.log("통신에러2");
                        }
                    });
                },
                error:function(){
                    // console.log("통신에러1");
                }
            });

        };
        navigator.geolocation.getCurrentPosition(geoSuccess);
    });
}



