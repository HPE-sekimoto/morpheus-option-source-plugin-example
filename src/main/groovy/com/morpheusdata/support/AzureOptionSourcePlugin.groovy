package com.morpheusdata.support

import com.morpheusdata.core.MorpheusContext
import com.morpheusdata.core.Plugin

class AzureOptionSourcePlugin extends Plugin {

	@Override
	String getCode() {
		return 'azure-option-source-plugin'
	}

	@Override
	void initialize() {
		this.setName('Azure Option Source Plugin')
		AzureOptionSourceProvider optionSourceProvider = new AzureOptionSourceProvider(this, morpheus)
		pluginProviders.put(optionSourceProvider.code, optionSourceProvider)
	}

	@Override
	void onDestroy() {
	}

	MorpheusContext getMorpheusContext() {
		return morpheus
	}
}
