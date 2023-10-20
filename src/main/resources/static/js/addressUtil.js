async function fetchProvince() {
    const response = await axios.get("/data/tinh_tp.json");
    return Object.values(response.data);
}

async function fetchDistrict(provinceCode) {
    const response = await axios.get("/data/quan-huyen/" + provinceCode + ".json");
    return Object.values(response.data);
}

async function fetchWard(districtCode) {
    const response = await axios.get("/data/xa-phuong/" + districtCode + ".json");
    return Object.values(response.data);
}
function toFullAddress({
                           provinces,
                           districts,
                           wards,
                           selectedProvince,
                           selectedDistrict,
                           selectedWard,
                           detailAddress,
                       }) {
    let province = provinces.find((p) => p.code == selectedProvince);
    let district = districts.find((d) => d.code == selectedDistrict);
    let ward = wards.find((w) => w.code == selectedWard);
    return `${detailAddress}, ${ward.name_with_type}, ${district.name_with_type}, ${province.name_with_type}`;
}
async function toAddress(fullAddress) {
    let address = fullAddress.split(",");
    let provinceIndex = address.length - 1;
    let districtIndex = address.length - 2;
    let wardIndex = address.length - 3;
    let provinces = await fetchProvince();
    let province = provinces.find(
        (p) => p.name_with_type.toLowerCase() == address[provinceIndex].trim().toLowerCase()
    );
    let districts = await fetchDistrict(province.code);
    let district = districts.find(
        (d) => d.name_with_type.toLowerCase() == address[districtIndex].trim().toLowerCase()
    );
    let wards = await fetchWard(district.code);
    let ward = wards.find((w) => w.name_with_type.toLowerCase() == address[wardIndex].trim().toLowerCase());
    console.log(province, district, ward);
    return {
        provinces: provinces,
        districts: districts,
        wards: wards,
        selectedProvince: province.code,
        selectedDistrict: district.code,
        selectedWard: ward.code,
        detailAddress: address.slice(0, wardIndex).join(","),
    };
}

document.addEventListener('alpine:init', () => {
    Alpine.data('addressPicker', () => ({
        provinces: {},
        districts: [],
        wards: [],
        selectedProvince: '',
        selectedDistrict: '',
        selectedWard:'',
        detailAddress: '',
        getfullAddress() {
            return toFullAddress(this);
        }
    }))
})