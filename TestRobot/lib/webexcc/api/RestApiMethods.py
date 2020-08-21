import requests
import yaml
import os
from robot.api import logger


class RestApiMethods:
    _data_ = None
    _response_ = None

    def __init__(self):
        path = os.getcwd()
        logger.info('This is current working directory : ' + str(path))
        with open(path+'/lib/webexcc/api/api.yaml') as dfile:
            self._data_ = yaml.safe_load(dfile)

    def request_method_test(self, data_center='QAUS1'):
        csm_requst_url = str(self._data_[data_center]['CSM_URL']) + str(self._data_[data_center]['ORG_ID'])
        authorize = str(self._data_[data_center]['AUTHORIZATION']) + ';' + ' tenantId=' + str(self._data_[data_center]['TENANTID'])
        logger.info('cms url is :' + csm_requst_url)
        logger.info('Authorize :' + authorize)
        logger.info('Conteny-type : ' + str(self._data_[data_center]['CONTENT-TYPE']))

        self._response_ = requests.get(
                                csm_requst_url,
                                 # params={'q': 'requests+language:python'},
                                headers={
                                    'From': self._data_[data_center]['FROM'],
                                    'Authorization' : authorize,
                                    'Content-Type' : self._data_[data_center]['CONTENT-TYPE']
                                         }
                                )

        json_response = self._response_.json()
        tenant_info = json_response['details']['tenant']
        logger.info('Tenant id is :' + str( {tenant_info["id"]}))